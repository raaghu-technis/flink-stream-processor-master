package com.google.pubsub.flink;

import com.google.auth.Credentials;
import com.google.common.base.Optional;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PubSubSink<T> extends PubSubSink<T> {

  private final String projectName;

  private final String topicName;

  private final PubSubSerializationSchema<T> serializationSchema;

  private final Optional<Credentials> credentials;

  private final Optional<Boolean> enableMessageOrdering;

  private final Optional<String> endpoint;

  private AutoValue_PubSubSink(
      String projectName,
      String topicName,
      PubSubSerializationSchema<T> serializationSchema,
      Optional<Credentials> credentials,
      Optional<Boolean> enableMessageOrdering,
      Optional<String> endpoint) {
    this.projectName = projectName;
    this.topicName = topicName;
    this.serializationSchema = serializationSchema;
    this.credentials = credentials;
    this.enableMessageOrdering = enableMessageOrdering;
    this.endpoint = endpoint;
  }

  @Override
  public String projectName() {
    return projectName;
  }

  @Override
  public String topicName() {
    return topicName;
  }

  @Override
  public PubSubSerializationSchema<T> serializationSchema() {
    return serializationSchema;
  }

  @Override
  public Optional<Credentials> credentials() {
    return credentials;
  }

  @Override
  public Optional<Boolean> enableMessageOrdering() {
    return enableMessageOrdering;
  }

  @Override
  public Optional<String> endpoint() {
    return endpoint;
  }

  @Override
  public String toString() {
    return "PubSubSink{"
        + "projectName=" + projectName + ", "
        + "topicName=" + topicName + ", "
        + "serializationSchema=" + serializationSchema + ", "
        + "credentials=" + credentials + ", "
        + "enableMessageOrdering=" + enableMessageOrdering + ", "
        + "endpoint=" + endpoint
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PubSubSink) {
      PubSubSink<?> that = (PubSubSink<?>) o;
      return this.projectName.equals(that.projectName())
          && this.topicName.equals(that.topicName())
          && this.serializationSchema.equals(that.serializationSchema())
          && this.credentials.equals(that.credentials())
          && this.enableMessageOrdering.equals(that.enableMessageOrdering())
          && this.endpoint.equals(that.endpoint());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= projectName.hashCode();
    h$ *= 1000003;
    h$ ^= topicName.hashCode();
    h$ *= 1000003;
    h$ ^= serializationSchema.hashCode();
    h$ *= 1000003;
    h$ ^= credentials.hashCode();
    h$ *= 1000003;
    h$ ^= enableMessageOrdering.hashCode();
    h$ *= 1000003;
    h$ ^= endpoint.hashCode();
    return h$;
  }

  static final class Builder<T> extends PubSubSink.Builder<T> {
    private String projectName;
    private String topicName;
    private PubSubSerializationSchema<T> serializationSchema;
    private Optional<Credentials> credentials = Optional.absent();
    private Optional<Boolean> enableMessageOrdering = Optional.absent();
    private Optional<String> endpoint = Optional.absent();
    Builder() {
    }
    @Override
    public PubSubSink.Builder<T> setProjectName(String projectName) {
      if (projectName == null) {
        throw new NullPointerException("Null projectName");
      }
      this.projectName = projectName;
      return this;
    }
    @Override
    public PubSubSink.Builder<T> setTopicName(String topicName) {
      if (topicName == null) {
        throw new NullPointerException("Null topicName");
      }
      this.topicName = topicName;
      return this;
    }
    @Override
    public PubSubSink.Builder<T> setSerializationSchema(PubSubSerializationSchema<T> serializationSchema) {
      if (serializationSchema == null) {
        throw new NullPointerException("Null serializationSchema");
      }
      this.serializationSchema = serializationSchema;
      return this;
    }
    @Override
    public PubSubSink.Builder<T> setCredentials(Credentials credentials) {
      this.credentials = Optional.of(credentials);
      return this;
    }
    @Override
    public PubSubSink.Builder<T> setEnableMessageOrdering(Boolean enableMessageOrdering) {
      this.enableMessageOrdering = Optional.of(enableMessageOrdering);
      return this;
    }
    @Override
    public PubSubSink.Builder<T> setEndpoint(String endpoint) {
      this.endpoint = Optional.of(endpoint);
      return this;
    }
    @Override
    public PubSubSink<T> build() {
      if (this.projectName == null
          || this.topicName == null
          || this.serializationSchema == null) {
        StringBuilder missing = new StringBuilder();
        if (this.projectName == null) {
          missing.append(" projectName");
        }
        if (this.topicName == null) {
          missing.append(" topicName");
        }
        if (this.serializationSchema == null) {
          missing.append(" serializationSchema");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_PubSubSink<T>(
          this.projectName,
          this.topicName,
          this.serializationSchema,
          this.credentials,
          this.enableMessageOrdering,
          this.endpoint);
    }
  }

}
