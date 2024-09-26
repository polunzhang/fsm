package com.polun.sample.entity.community;

public record UserId(String value) {

  public static final String BOT = "bot";

  public static UserId of(String value) {
    if (value == null) {
      throw new IllegalArgumentException("User ID cannot be null");
    }
    if (value.length() > 20) {
      throw new IllegalArgumentException("User ID cannot be longer than 20 characters");
    }
    return new UserId(value);
  }

  public boolean isBot() {
    return value.equals(BOT);
  }
}
