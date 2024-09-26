package com.polun.sample.adapter;

import com.polun.sample.entity.community.UserId;

public class StartBroadcastAdapter extends JsonToObjectAdapter<UserId> {

  @Override
  protected UserId convert(String json) {
    try {
      Data data = super.objectMapper.readValue(json, Data.class);
      return UserId.of(data.speakerId);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to convert JSON to Message: " + e.getMessage(), e);
    }
  }

  private record Data(String speakerId) {}
}
