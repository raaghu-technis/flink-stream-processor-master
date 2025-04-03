package com.google.pubsub.flink;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.pubsub.v1.PubsubMessage;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PubSubMessageParser {
    private static final Logger LOG = LoggerFactory.getLogger(PubSubMessageParser.class);
    private static final Set<String> TARGET_DEVICE_IDS = new HashSet<>(Arrays.asList("DAHUA_DUAL-LENS_8D05925PAG255BC"));

    private static String getDeviceIdFromPath(String path) {
        if (path == null || path.isEmpty()) {
            LOG.debug("Path is null or empty");
            return "unknown";
        }
        String[] parts = path.split("/");
        if (parts.length < 3) {
            LOG.debug("Invalid path format: " + path);
            return "unknown";
        }
        String deviceId = parts[2].trim();
        LOG.info("Extracted device ID: '" + deviceId + "' (length: " + deviceId.length() + ") from path: '" + path + "'");
        return deviceId;
    }

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String projectName = "technis-counting-dev-11983";
        //String subscriptionName = "tsda-beam-spike";
        String subscriptionName = "flink-spike-delete-me";

        // For running locally, Comment the below code
        // Creds will be passed using environment variable during local run
        /*GoogleCredentials creds = GoogleCredentials
                .fromStream(new FileInputStream("/opt/flink/application_default_credentials.json"));
        LOG.info("Successfully loaded credentials: {}", creds);*/

        LOG.info("Starting PubSub consumer with project: " + projectName + ", subscription: " + subscriptionName);
        LOG.info("Target device ID: '" + TARGET_DEVICE_IDS.iterator().next() + "' (length: " + TARGET_DEVICE_IDS.iterator().next().length() + ")");

        DataStream<String> source = env.fromSource(
                PubSubSource.<String>builder()
                        .setDeserializationSchema(new PubSubDeserializationSchema<String>() {
                            private final ObjectMapper mapper = new ObjectMapper();

                            @Override
                            public String deserialize(PubsubMessage message) throws IOException {
                                LOG.info("\n=== New Message Received ===");
                                LOG.info("Message ID: " + message.getMessageId());

                                String path = message.getAttributesMap().getOrDefault("path", "").trim();
                                LOG.info("Path attribute: '" + path + "'");

                                String deviceId = getDeviceIdFromPath(path);
                                LOG.info("Comparing device IDs:");
                                LOG.info("  Expected: '" + TARGET_DEVICE_IDS.iterator().next() + "'");
                                LOG.info("  Actual:   '" + deviceId + "'");
                                LOG.info("  Match:     " + TARGET_DEVICE_IDS.contains(deviceId));

                                if (!TARGET_DEVICE_IDS.contains(deviceId)) {
                                    LOG.info("Skipping message - not from target device");
                                    return null;
                                }

                                // Only print this for matches, regardless of debug mode
                                LOG.info("\n=== Matched Message ===");
                                LOG.info("Device ID: " + deviceId);

                                ObjectNode node = mapper.createObjectNode();
                                try {
                                    String data = message.getData().toStringUtf8();
                                    LOG.info("Message data: " + data);
                                    node.set("data", mapper.readTree(data));
                                } catch (Exception e) {
                                    LOG.info("Error parsing data: " + e.getMessage());
                                    node.put("data", message.getData().toStringUtf8());
                                }

                                node.put("device_id", deviceId);
                                node.put("publishTime", message.getPublishTime().getSeconds());

                                String result = mapper.writeValueAsString(node);
                                // Print the result for matches, regardless of debug mode
                                LOG.info("Message: " + result);
                                LOG.info("=== End Matched Message ===\n");
                                return result;
                            }

                            private String getCharCodes(String str) {
                                StringBuilder codes = new StringBuilder();
                                for (char c : str.toCharArray()) {
                                    codes.append((int) c).append(" ");
                                }
                                return codes.toString();
                            }

                            @Override
                            public void open(DeserializationSchema.InitializationContext context) {
                                LOG.info("Initializing deserializer");
                            }

                            @Override
                            public TypeInformation<String> getProducedType() {
                                return Types.STRING;
                            }
                        })
                        .setProjectName(projectName)
                        //.setCredentials(creds)  // Comment this line for local mode
                        .setSubscriptionName(subscriptionName)
                        .build(),
                WatermarkStrategy.noWatermarks(),
                "PubSubSource"
        );

        DataStream<String> filteredSource = source.filter(message -> {
            if (message == null) {
                LOG.info("Filtered out null message");
                return false;
            }
            return true;
        });

        LOG.info("Starting to process messages...");
        filteredSource.print();
        env.execute("PubSub Message Parser");
    }
}