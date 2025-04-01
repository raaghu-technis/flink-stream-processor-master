package com.google.pubsub.flink;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.pubsub.v1.PubsubMessage;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.JsonNode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CounterStateExample {
    private static final Set<String> TARGET_DEVICE_IDS = new HashSet<>(Arrays.asList("DAHUA_DUAL-LENS_8D05925PAG255BC"));
    private static final boolean DEBUG_MODE = true; // Set to true for verbose output, false for matches only

    // Data model classes
    public static class CounterState {
        public int prevEntered;
        public int prevExited;

        public CounterState() {
            this.prevEntered = 0;
            this.prevExited = 0;
        }
    }

    public static class OutputRecord {
        public final String deviceId;
        public final long timestampMs;
        public final int deltaEntered;
        public final int deltaExited;

        public OutputRecord(String deviceId, long timestampMs, int deltaEntered, int deltaExited) {
            this.deviceId = deviceId;
            this.timestampMs = timestampMs;
            this.deltaEntered = deltaEntered;
            this.deltaExited = deltaExited;
        }

        @Override
        public String toString() {
            return String.format("Device: %s, Time: %d, ΔEntered: %d, ΔExited: %d",
                    deviceId, timestampMs, deltaEntered, deltaExited);
        }
    }

    // Stateful processing function
    public static class ComputeDeltaFunction extends KeyedProcessFunction<String, JsonNode, OutputRecord> {
        private ValueState<CounterState> counterState;
        private final ObjectMapper mapper = new ObjectMapper();

        @Override
        public void open(Configuration parameters) {
            ValueStateDescriptor<CounterState> descriptor =
                    new ValueStateDescriptor<>("counter-state", CounterState.class);
            counterState = getRuntimeContext().getState(descriptor);
        }

        @Override
        public void processElement(JsonNode value, Context ctx, Collector<OutputRecord> out) throws Exception {
            String deviceId = value.get("device_id").asText();
            JsonNode rules = value.get("data").get("Rules");

            if (rules != null && rules.size() > 0) {
                JsonNode firstRule = rules.get(0);
                int currentEntered = firstRule.get("EnteredSubtotal").asInt();
                int currentExited = firstRule.get("ExitedSubtotal").asInt();

                CounterState state = counterState.value();
                if (state == null) {
                    state = new CounterState();
                }

                int deltaEntered = currentEntered - state.prevEntered;
                int deltaExited = currentExited - state.prevExited;

                if (DEBUG_MODE) {
                    System.out.printf("device: %s, delta_entered: %d, delta_exited: %d%n",
                            deviceId, deltaEntered, deltaExited);
                }

                // Update state
                state.prevEntered = currentEntered;
                state.prevExited = currentExited;
                counterState.update(state);

                // Emit if there's a positive change
                if (deltaEntered > 0 || deltaExited > 0) {
                    long timestampMs = ctx.timestamp();
                    out.collect(new OutputRecord(deviceId, timestampMs, deltaEntered, deltaExited));
                }
            }
        }
    }

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

        // Setup Google Credentials

        System.out.println("**********Loading Creds File: *************");

        GoogleCredentials creds = GoogleCredentials
                .fromStream(new FileInputStream("/opt/flink/application_default_credentials.json"));

        System.out.println("**********Loaded Creds File: *************" + creds.toString());

        debugPrint("Starting PubSub consumer with project: " + projectName + ", subscription: " + subscriptionName);
        debugPrint("Target device ID: '" + TARGET_DEVICE_IDS.iterator().next() + "' (length: " + TARGET_DEVICE_IDS.iterator().next().length() + ")");

        DataStream<JsonNode> source = env.fromSource(
                PubSubSource.<JsonNode>builder()
                        .setDeserializationSchema(new PubSubDeserializationSchema<JsonNode>() {
                            private final ObjectMapper mapper = new ObjectMapper();

                            @Override
                            public JsonNode deserialize(PubsubMessage message) throws IOException {
                                String path = message.getAttributesMap().getOrDefault("path", "").trim();
                                String deviceId = getDeviceIdFromPath(path);

                                if (!TARGET_DEVICE_IDS.contains(deviceId)) {
                                    return null;
                                }

                                ObjectNode node = mapper.createObjectNode();
                                try {
                                    node.set("data", mapper.readTree(message.getData().toStringUtf8()));
                                    node.put("device_id", deviceId);
                                    node.put("publishTime", message.getPublishTime().getSeconds());
                                    return node;
                                } catch (Exception e) {
                                    debugPrint("Error parsing data: " + e.getMessage());
                                    return null;
                                }
                            }

                            @Override
                            public void open(DeserializationSchema.InitializationContext context) {
                                debugPrint("Initializing deserializer");
                            }

                            @Override
                            public TypeInformation<JsonNode> getProducedType() {
                                return TypeInformation.of(JsonNode.class);
                            }
                        })
                        .setProjectName(projectName)
                        .setSubscriptionName(subscriptionName)
                        .setCredentials(creds)
                        .build(),
                WatermarkStrategy.noWatermarks(),
                "PubSubSource"
        );

        // Apply stateful processing
        DataStream<OutputRecord> processedStream = source
                .filter(message -> message != null)
                .keyBy(node -> node.get("device_id").asText())
                .process(new ComputeDeltaFunction());

        // Print the results
        processedStream.print();

        env.execute("PubSub Counter Delta Processor");
    }
}