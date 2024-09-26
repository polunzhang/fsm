package com.polun.sample.entity.fsm;

import java.util.concurrent.atomic.AtomicInteger;
import lombok.Getter;

public class Command {

  @Getter private final String value;
  private AtomicInteger quota;
  private final boolean isInfinite;

  public Command(String value, int quota) {
    this.value = value;
    if (quota < 0) throw new IllegalArgumentException("Quota cannot be negative");
    this.quota = new AtomicInteger(quota);
    this.isInfinite = quota == 0;
  }

  public void decreaseQuota() {
    if(quota.get() != 0){
      quota.decrementAndGet();
    }
  }

  public boolean isQuotaEnough() {
    return isInfinite || quota.get() > 0;
  }
}
