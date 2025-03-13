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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PubSubExample {
    private static final Set<String> TARGET_DEVICE_IDS = new HashSet<>(Arrays.asList("DAHUA_DUAL-LENS_8D05925PAG255BC"));
    private static final boolean DEBUG_MODE = false; // Set to true for verbose output, false for matches only

    private static void debugPrint(String message) {
        if (DEBUG_MODE) {
            System.out.println(message);
        }
    }

    private static String getDeviceIdFromPath(String path) {
        if (path == null || path.isEmpty()) {
            debugPrint("Path is null or empty");
            return "unknown";
        }
        String[] parts = path.split("/");
        if (parts.length < 3) {
            debugPrint("Invalid path format: " + path);
            return "unknown";
        }
        String deviceId = parts[2].trim();
        debugPrint("Extracted device ID: '" + deviceId + "' (length: " + deviceId.length() + ") from path: '" + path + "'");
        return deviceId;
    }

    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        String projectName = "technis-counting-dev-11983";
        String subscriptionName = "tsda-beam-spike";

        debugPrint("Starting PubSub consumer with project: " + projectName + ", subscription: " + subscriptionName);
        debugPrint("Target device ID: '" + TARGET_DEVICE_IDS.iterator().next() + "' (length: " + TARGET_DEVICE_IDS.iterator().next().length() + ")");

        DataStream<String> source = env.fromSource(
                PubSubSource.<String>builder()
                        .setDeserializationSchema(new PubSubDeserializationSchema<String>() {
                            private final ObjectMapper mapper = new ObjectMapper();

                            @Override
                            public String deserialize(PubsubMessage message) throws IOException {
                                debugPrint("\n=== New Message Received ===");
                                debugPrint("Message ID: " + message.getMessageId());

                                String path = message.getAttributesMap().getOrDefault("path", "").trim();
                                debugPrint("Path attribute: '" + path + "'");

                                String deviceId = getDeviceIdFromPath(path);
                                debugPrint("Comparing device IDs:");
                                debugPrint("  Expected: '" + TARGET_DEVICE_IDS.iterator().next() + "'");
                                debugPrint("  Actual:   '" + deviceId + "'");
                                debugPrint("  Match:     " + TARGET_DEVICE_IDS.contains(deviceId));

                                if (!TARGET_DEVICE_IDS.contains(deviceId)) {
                                    debugPrint("Skipping message - not from target device");
                                    if (DEBUG_MODE) {
                                        debugPrint("Character codes in deviceId: " + getCharCodes(deviceId));
                                        debugPrint("Character codes in target: " + getCharCodes(TARGET_DEVICE_IDS.iterator().next()));
                                    }
                                    return null;
                                }

                                // Only print this for matches, regardless of debug mode
                                System.out.println("\n=== Matched Message ===");
                                System.out.println("Device ID: " + deviceId);

                                ObjectNode node = mapper.createObjectNode();
                                try {
                                    String data = message.getData().toStringUtf8();
                                    debugPrint("Message data: " + data);
                                    node.set("data", mapper.readTree(data));
                                } catch (Exception e) {
                                    debugPrint("Error parsing data: " + e.getMessage());
                                    node.put("data", message.getData().toStringUtf8());
                                }

                                node.put("device_id", deviceId);
                                node.put("publishTime", message.getPublishTime().getSeconds());

                                String result = mapper.writeValueAsString(node);
                                // Print the result for matches, regardless of debug mode
                                System.out.println("Message: " + result);
                                System.out.println("=== End Matched Message ===\n");
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
                                debugPrint("Initializing deserializer");
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

        DataStream<String> filteredSource = source.filter(message -> {
            if (message == null) {
                debugPrint("Filtered out null message");
                return false;
            }
            return true;
        });

        debugPrint("Starting to process messages...");
        filteredSource.print();
        env.execute("PubSub Message Parser");
    }
}