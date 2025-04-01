# Start from the official Flink 1.20 image
FROM flink:1.20

# Copy your JAR into /opt/flink
COPY line-counting-stateful.jar /opt/flink/line-counting-stateful.jar

# Copy Google Credentails
COPY application_default_credentials.json /opt/flink/application_default_credentials.json
