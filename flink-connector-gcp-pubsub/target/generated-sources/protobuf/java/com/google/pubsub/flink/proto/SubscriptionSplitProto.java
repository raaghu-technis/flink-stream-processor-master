// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: split.proto

package com.google.pubsub.flink.proto;

/**
 * Protobuf type {@code google.pubsub.flink.SubscriptionSplitProto}
 */
public  final class SubscriptionSplitProto extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.pubsub.flink.SubscriptionSplitProto)
    SubscriptionSplitProtoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use SubscriptionSplitProto.newBuilder() to construct.
  private SubscriptionSplitProto(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private SubscriptionSplitProto() {
    subscription_ = "";
    uid_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private SubscriptionSplitProto(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            subscription_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            uid_ = s;
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.pubsub.flink.proto.Split.internal_static_google_pubsub_flink_SubscriptionSplitProto_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.pubsub.flink.proto.Split.internal_static_google_pubsub_flink_SubscriptionSplitProto_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.pubsub.flink.proto.SubscriptionSplitProto.class, com.google.pubsub.flink.proto.SubscriptionSplitProto.Builder.class);
  }

  public static final int SUBSCRIPTION_FIELD_NUMBER = 1;
  private volatile java.lang.Object subscription_;
  /**
   * <pre>
   * The full Pub/Sub subscription path for this split.
   * </pre>
   *
   * <code>string subscription = 1;</code>
   */
  public java.lang.String getSubscription() {
    java.lang.Object ref = subscription_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      subscription_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * The full Pub/Sub subscription path for this split.
   * </pre>
   *
   * <code>string subscription = 1;</code>
   */
  public com.google.protobuf.ByteString
      getSubscriptionBytes() {
    java.lang.Object ref = subscription_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      subscription_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int UID_FIELD_NUMBER = 2;
  private volatile java.lang.Object uid_;
  /**
   * <pre>
   * Unique identifier for this subscription's split.
   * </pre>
   *
   * <code>string uid = 2;</code>
   */
  public java.lang.String getUid() {
    java.lang.Object ref = uid_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      uid_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Unique identifier for this subscription's split.
   * </pre>
   *
   * <code>string uid = 2;</code>
   */
  public com.google.protobuf.ByteString
      getUidBytes() {
    java.lang.Object ref = uid_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      uid_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!getSubscriptionBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, subscription_);
    }
    if (!getUidBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, uid_);
    }
    unknownFields.writeTo(output);
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSubscriptionBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, subscription_);
    }
    if (!getUidBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, uid_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.google.pubsub.flink.proto.SubscriptionSplitProto)) {
      return super.equals(obj);
    }
    com.google.pubsub.flink.proto.SubscriptionSplitProto other = (com.google.pubsub.flink.proto.SubscriptionSplitProto) obj;

    boolean result = true;
    result = result && getSubscription()
        .equals(other.getSubscription());
    result = result && getUid()
        .equals(other.getUid());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + SUBSCRIPTION_FIELD_NUMBER;
    hash = (53 * hash) + getSubscription().hashCode();
    hash = (37 * hash) + UID_FIELD_NUMBER;
    hash = (53 * hash) + getUid().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.pubsub.flink.proto.SubscriptionSplitProto parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.pubsub.flink.proto.SubscriptionSplitProto prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code google.pubsub.flink.SubscriptionSplitProto}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.pubsub.flink.SubscriptionSplitProto)
      com.google.pubsub.flink.proto.SubscriptionSplitProtoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.pubsub.flink.proto.Split.internal_static_google_pubsub_flink_SubscriptionSplitProto_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.pubsub.flink.proto.Split.internal_static_google_pubsub_flink_SubscriptionSplitProto_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.pubsub.flink.proto.SubscriptionSplitProto.class, com.google.pubsub.flink.proto.SubscriptionSplitProto.Builder.class);
    }

    // Construct using com.google.pubsub.flink.proto.SubscriptionSplitProto.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    public Builder clear() {
      super.clear();
      subscription_ = "";

      uid_ = "";

      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.pubsub.flink.proto.Split.internal_static_google_pubsub_flink_SubscriptionSplitProto_descriptor;
    }

    public com.google.pubsub.flink.proto.SubscriptionSplitProto getDefaultInstanceForType() {
      return com.google.pubsub.flink.proto.SubscriptionSplitProto.getDefaultInstance();
    }

    public com.google.pubsub.flink.proto.SubscriptionSplitProto build() {
      com.google.pubsub.flink.proto.SubscriptionSplitProto result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.google.pubsub.flink.proto.SubscriptionSplitProto buildPartial() {
      com.google.pubsub.flink.proto.SubscriptionSplitProto result = new com.google.pubsub.flink.proto.SubscriptionSplitProto(this);
      result.subscription_ = subscription_;
      result.uid_ = uid_;
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.pubsub.flink.proto.SubscriptionSplitProto) {
        return mergeFrom((com.google.pubsub.flink.proto.SubscriptionSplitProto)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.pubsub.flink.proto.SubscriptionSplitProto other) {
      if (other == com.google.pubsub.flink.proto.SubscriptionSplitProto.getDefaultInstance()) return this;
      if (!other.getSubscription().isEmpty()) {
        subscription_ = other.subscription_;
        onChanged();
      }
      if (!other.getUid().isEmpty()) {
        uid_ = other.uid_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.google.pubsub.flink.proto.SubscriptionSplitProto parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.google.pubsub.flink.proto.SubscriptionSplitProto) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object subscription_ = "";
    /**
     * <pre>
     * The full Pub/Sub subscription path for this split.
     * </pre>
     *
     * <code>string subscription = 1;</code>
     */
    public java.lang.String getSubscription() {
      java.lang.Object ref = subscription_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        subscription_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * The full Pub/Sub subscription path for this split.
     * </pre>
     *
     * <code>string subscription = 1;</code>
     */
    public com.google.protobuf.ByteString
        getSubscriptionBytes() {
      java.lang.Object ref = subscription_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        subscription_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * The full Pub/Sub subscription path for this split.
     * </pre>
     *
     * <code>string subscription = 1;</code>
     */
    public Builder setSubscription(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      subscription_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The full Pub/Sub subscription path for this split.
     * </pre>
     *
     * <code>string subscription = 1;</code>
     */
    public Builder clearSubscription() {
      
      subscription_ = getDefaultInstance().getSubscription();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * The full Pub/Sub subscription path for this split.
     * </pre>
     *
     * <code>string subscription = 1;</code>
     */
    public Builder setSubscriptionBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      subscription_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object uid_ = "";
    /**
     * <pre>
     * Unique identifier for this subscription's split.
     * </pre>
     *
     * <code>string uid = 2;</code>
     */
    public java.lang.String getUid() {
      java.lang.Object ref = uid_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        uid_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Unique identifier for this subscription's split.
     * </pre>
     *
     * <code>string uid = 2;</code>
     */
    public com.google.protobuf.ByteString
        getUidBytes() {
      java.lang.Object ref = uid_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        uid_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Unique identifier for this subscription's split.
     * </pre>
     *
     * <code>string uid = 2;</code>
     */
    public Builder setUid(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      uid_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Unique identifier for this subscription's split.
     * </pre>
     *
     * <code>string uid = 2;</code>
     */
    public Builder clearUid() {
      
      uid_ = getDefaultInstance().getUid();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Unique identifier for this subscription's split.
     * </pre>
     *
     * <code>string uid = 2;</code>
     */
    public Builder setUidBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      uid_ = value;
      onChanged();
      return this;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:google.pubsub.flink.SubscriptionSplitProto)
  }

  // @@protoc_insertion_point(class_scope:google.pubsub.flink.SubscriptionSplitProto)
  private static final com.google.pubsub.flink.proto.SubscriptionSplitProto DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.pubsub.flink.proto.SubscriptionSplitProto();
  }

  public static com.google.pubsub.flink.proto.SubscriptionSplitProto getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<SubscriptionSplitProto>
      PARSER = new com.google.protobuf.AbstractParser<SubscriptionSplitProto>() {
    public SubscriptionSplitProto parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new SubscriptionSplitProto(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<SubscriptionSplitProto> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<SubscriptionSplitProto> getParserForType() {
    return PARSER;
  }

  public com.google.pubsub.flink.proto.SubscriptionSplitProto getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

