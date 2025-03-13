package com.google.pubsub.flink.internal.source.split;

import com.google.pubsub.v1.ProjectSubscriptionName;
import javax.annotation.processing.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
final class AutoValue_SubscriptionSplit extends SubscriptionSplit {

  private final ProjectSubscriptionName subscriptionName;

  private final String uid;

  AutoValue_SubscriptionSplit(
      ProjectSubscriptionName subscriptionName,
      String uid) {
    if (subscriptionName == null) {
      throw new NullPointerException("Null subscriptionName");
    }
    this.subscriptionName = subscriptionName;
    if (uid == null) {
      throw new NullPointerException("Null uid");
    }
    this.uid = uid;
  }

  @Override
  public ProjectSubscriptionName subscriptionName() {
    return subscriptionName;
  }

  @Override
  public String uid() {
    return uid;
  }

  @Override
  public String toString() {
    return "SubscriptionSplit{"
        + "subscriptionName=" + subscriptionName + ", "
        + "uid=" + uid
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof SubscriptionSplit) {
      SubscriptionSplit that = (SubscriptionSplit) o;
      return this.subscriptionName.equals(that.subscriptionName())
          && this.uid.equals(that.uid());
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= subscriptionName.hashCode();
    h$ *= 1000003;
    h$ ^= uid.hashCode();
    return h$;
  }

}
