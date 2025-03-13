package com.google.pubsub.flink;

import com.google.auth.Credentials;
import com.google.common.base.Optional;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_PubSubSource<OutputT> extends PubSubSource<OutputT> {

  private final String projectName;

  private final String subscriptionName;

  private final PubSubDeserializationSchema<OutputT> deserializationSchema;

  private final Optional<Long> maxOutstandingMessagesCount;

  private final Optional<Long> maxOutstandingMessagesBytes;

  private final Optional<Integer> parallelPullCount;

  private final Optional<Credentials> credentials;

  private final Optional<String> endpoint;

  private AutoValue_PubSubSource(
      String projectName,
      String subscriptionName,
      PubSubDeserializationSchema<OutputT> deserializationSchema,
      Optional<Long> maxOutstandingMessagesCount,
      Optional<Long> maxOutstandingMessagesBytes,
      Optional<Integer> parallelPullCount,
      Optional<Credentials> credentials,
      Optional<String> endpoint) {
    this.projectName = projectName;
    this.subscriptionName = subscriptionName;
    this.deserializationSchema = deserializationSchema;
    this.maxOutstandingMessagesCount = maxOutstandingMessagesCount;
    this.maxOutstandingMessagesBytes = maxOutstandingMessagesBytes;
    this.parallelPullCount = parallelPullCount;
    this.credentials = credentials;
    this.endpoint = endpoint;
  }

  @Override
  public String projectName() {
    return projectName;
  }

  @Override
  public String subscriptionName() {
    return subscriptionName;
  }

  @Override
  public PubSubDeserializationSchema<OutputT> deserializationSchema() {
    return deserializationSchema;
  }

  @Override
  public Optional<Long> maxOutstandingMessagesCount() {
    return maxOutstandingMessagesCount;
  }

  @Override
  public Optional<Long> maxOutstandingMessagesBytes() {
    return maxOutstandingMessagesBytes;
  }

  @Override
  public Optional<Integer> parallelPullCount() {
    return parallelPullCount;
  }

  @Override
  public Optional<Credentials> credentials() {
    return credentials;
  }

  @Override
  public Optional<String> endpoint() {
    return endpoint;
  }

  @Override
  public String toString() {
    return "PubSubSource{"
        + "projectName=" + projectName + ", "
        + "subscriptionName=" + subscriptionName + ", "
        + "deserializationSchema=" + deserializationSchema + ", "
        + "maxOutstandingMessagesCount=" + maxOutstandingMessagesCount + ", "
        + "maxOutstandingMessagesBytes=" + maxOutstandingMessagesBytes + ", "
        + "parallelPullCount=" + parallelPullCount + ", "
        + "credentials=" + credentials + ", "
        + "endpoint=" + endpoint
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof PubSubSource) {
      PubSubSource<?> that = (PubSubSource<?>) o;
      return this.projectName.equals(that.projectName())
          && this.subscriptionName.equals(that.subscriptionName())
          && this.deserializationSchema.equals(that.deserializationSchema())
          && this.maxOutstandingMessagesCount.equals(that.maxOutstandingMessagesCount())
          && this.maxOutstandingMessagesBytes.equals(that.maxOutstandingMessagesBytes())
          && this.parallelPullCount.equals(that.parallelPullCount())
          && this.credentials.equals(that.credentials())
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
    h$ ^= subscriptionName.hashCode();
    h$ *= 1000003;
    h$ ^= deserializationSchema.hashCode();
    h$ *= 1000003;
    h$ ^= maxOutstandingMessagesCount.hashCode();
    h$ *= 1000003;
    h$ ^= maxOutstandingMessagesBytes.hashCode();
    h$ *= 1000003;
    h$ ^= parallelPullCount.hashCode();
    h$ *= 1000003;
    h$ ^= credentials.hashCode();
    h$ *= 1000003;
    h$ ^= endpoint.hashCode();
    return h$;
  }

  static final class Builder<OutputT> extends PubSubSource.Builder<OutputT> {
    private String projectName;
    private String subscriptionName;
    private PubSubDeserializationSchema<OutputT> deserializationSchema;
    private Optional<Long> maxOutstandingMessagesCount = Optional.absent();
    private Optional<Long> maxOutstandingMessagesBytes = Optional.absent();
    private Optional<Integer> parallelPullCount = Optional.absent();
    private Optional<Credentials> credentials = Optional.absent();
    private Optional<String> endpoint = Optional.absent();
    Builder() {
    }
    @Override
    public PubSubSource.Builder<OutputT> setProjectName(String projectName) {
      if (projectName == null) {
        throw new NullPointerException("Null projectName");
      }
      this.projectName = projectName;
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setSubscriptionName(String subscriptionName) {
      if (subscriptionName == null) {
        throw new NullPointerException("Null subscriptionName");
      }
      this.subscriptionName = subscriptionName;
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setDeserializationSchema(PubSubDeserializationSchema<OutputT> deserializationSchema) {
      if (deserializationSchema == null) {
        throw new NullPointerException("Null deserializationSchema");
      }
      this.deserializationSchema = deserializationSchema;
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setMaxOutstandingMessagesCount(Long maxOutstandingMessagesCount) {
      this.maxOutstandingMessagesCount = Optional.of(maxOutstandingMessagesCount);
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setMaxOutstandingMessagesBytes(Long maxOutstandingMessagesBytes) {
      this.maxOutstandingMessagesBytes = Optional.of(maxOutstandingMessagesBytes);
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setParallelPullCount(Integer parallelPullCount) {
      this.parallelPullCount = Optional.of(parallelPullCount);
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setCredentials(Credentials credentials) {
      this.credentials = Optional.of(credentials);
      return this;
    }
    @Override
    public PubSubSource.Builder<OutputT> setEndpoint(String endpoint) {
      this.endpoint = Optional.of(endpoint);
      return this;
    }
    @Override
    PubSubSource<OutputT> autoBuild() {
      if (this.projectName == null
          || this.subscriptionName == null
          || this.deserializationSchema == null) {
        StringBuilder missing = new StringBuilder();
        if (this.projectName == null) {
          missing.append(" projectName");
        }
        if (this.subscriptionName == null) {
          missing.append(" subscriptionName");
        }
        if (this.deserializationSchema == null) {
          missing.append(" deserializationSchema");
        }
        throw new IllegalStateException("Missing required properties:" + missing);
      }
      return new AutoValue_PubSubSource<OutputT>(
          this.projectName,
          this.subscriptionName,
          this.deserializationSchema,
          this.maxOutstandingMessagesCount,
          this.maxOutstandingMessagesBytes,
          this.parallelPullCount,
          this.credentials,
          this.endpoint);
    }
  }

}
