package com.polun.sample.entity.community.forum;


public record PostId(String value) {
  public static PostId of(String value) {
    return new PostId(value);
  }
}
