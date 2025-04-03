# Flink Stream Processor

### Building pubsub connector and application jar

Prerequisites:
*   Maven (recommended version 3.8.6)
*   Java 11

```sh
git clone https://github.com/raaghu-technis/flink-stream-processor-master.git
mvn clean package -DskipTests
```

The resulting jars can be found in the `target` directory of the respective
module.

The connector library JAR file is
`flink-connector-gcp-pubsub/target/flink-connector-gcp-pubsub-1.0.0-SNAPSHOT.jar`.

The application JAR file is
`flink-stream-processor-gcp-pubsub/pubsub-streaming/target/line-counter-stateful.jar`.

### Running the Flink Job

This section describes how to build the Flink job located under
`flink-stream-processor-gcp-pubsub/`, deploy it to a GKE Cluster and verify that it
is running correctly. The flink job streams data from a Pub/Sub subscription
source and applies stateful processing.

### TODO
    - Add steps for GKE deployment