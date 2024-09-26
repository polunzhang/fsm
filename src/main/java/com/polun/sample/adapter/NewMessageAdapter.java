package com.polun.sample.adapter;

import com.polun.sample.entity.community.chatroom.Message;

public class NewMessageAdapter extends JsonToObjectAdapter<Message> {

  @Override
  protected Message convert(String json) {
    try {
      Data data = super.objectMapper.readValue(json, Data.class);
      return Message.of(data.authorId, data.content, data.tags);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to convert JSON to Message: " + e.getMessage(), e);
    }
  }

  private record Data(String authorId, String content, String[] tags) {}
}
