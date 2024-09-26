package com.polun.sample.adapter;

import com.polun.sample.entity.community.User;

public class LoginAdapter extends JsonToObjectAdapter<User> {

  @Override
  protected User convert(String json) {
    try {
      Data data = super.objectMapper.readValue(json, Data.class);
      return new User(data.userId, data.isAdmin);
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to convert JSON to Message: " + e.getMessage(), e);
    }
  }

  private record Data(String userId, boolean isAdmin) {}
}
