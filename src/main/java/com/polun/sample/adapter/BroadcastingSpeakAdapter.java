package com.polun.sample.adapter;

import com.polun.sample.entity.community.broadcast.Speak;

public class BroadcastingSpeakAdapter extends JsonToObjectAdapter<Speak> {

  @Override
  protected Speak convert(String json) {
    try {
      Data data = super.objectMapper.readValue(json, Data.class);
      return Speak.of(data.speakerId, data.content);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to convert JSON to Message: " + e.getMessage(), e);
    }
  }

  private record Data(String speakerId, String content) {}
}
