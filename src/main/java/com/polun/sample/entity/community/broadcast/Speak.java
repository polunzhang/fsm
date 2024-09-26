package com.polun.sample.entity.community.broadcast;

import com.polun.sample.entity.community.UserId;

public record Speak(UserId speakId, String context) {
  public static Speak of(String speakId, String context) {
    return new Speak(UserId.of(speakId), context);
  }
}
