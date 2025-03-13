package com.google.pubsub.flink;

import com.google.pubsub.v1.PubsubMessage;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PubSubExample {
    private static final Logger LOG = LoggerFactory.getLogger(PubSubExample.class);
    private static final Set<String> TARGET_DEVICE_IDS = new HashSet<>(Arrays.asList("DAHUA_DUAL-LENS_8D05925PAG255BC")); // Add your target device IDs

    private static String getDeviceIdFromPath(String path) {
        if (path == null || path.isEmpty()) {
            return "unknown";
        }
        String[] parts = path.split("/");
        if (parts.length < 3) { // We need at least ["", "device_handler", "device_id"]
            return "unknown";
        }
        return parts[2]; // Index 2 contains device_id
    }

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String projectName = "technis-counting-dev-11983";
        String subscriptionName = "tsda-beam-spike";

        DataStream<String> source = env.fromSource(
                PubSubSource.<String>builder()
                        .setDeserializationSchema(new PubSubDeserializationSchema<String>() {
                            private final ObjectMapper mapper = new ObjectMapper();

                            @Override
                            public String deserialize(PubsubMessage message) throws IOException {
                                // Extract device ID from path attribute
                                String path = message.getAttributesMap().getOrDefault("path", "");
                                String deviceId = getDeviceIdFromPath(path);

                                // Only process messages from target devices
                                if (!TARGET_DEVICE_IDS.contains(deviceId)) {
                                    return null; // This message will be filtered out
                                }

                                ObjectNode node = mapper.createObjectNode();
                                // Parse the data as JSON and include it in the output
                                try {
                                    node.set("data", mapper.readTree(message.getData().toStringUtf8()));
                                } catch (Exception e) {
                                    // If data is not JSON, store it as a string
                                    node.put("data", message.getData().toStringUtf8());
                                }

                                // Add device_id to the output
                                node.put("device_id", deviceId);
                                // Get all attributes as a map
                                // node.set("attributes", mapper.valueToTree(message.getAttributesMap()));
                                // Get message ID
                                // node.put("messageId", message.getMessageId());
                                // Get publish time
                                node.put("publishTime", message.getPublishTime().getSeconds());
                                // Get ordering key
                                //node.put("orderingKey", message.getOrderingKey());

                                return mapper.writeValueAsString(node);
                            }

                            @Override
                            public void open(DeserializationSchema.InitializationContext context) {
                                // No initialization needed
                            }

                            @Override
                            public TypeInformation<String> getProducedType() {
                                return Types.STRING;
                            }
                        })
                        .setProjectName(projectName)
                        .setSubscriptionName(subscriptionName)
                        .build(),
                WatermarkStrategy.noWatermarks(),
                "PubSubSource"
        );

        // Filter out null messages (non-matching device IDs)
        DataStream<String> filteredSource = source.filter(message -> message != null);

        filteredSource.print();
        env.execute("PubSub Message Parser");
    }
}