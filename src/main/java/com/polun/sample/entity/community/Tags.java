package com.polun.sample.entity.community;

import java.util.Arrays;
import java.util.List;

public record Tags(List<UserId> value) {
  public static Tags of(String[] userIds) {
    if (userIds == null || userIds.length == 0) return new Tags(List.of());
    return new Tags(Arrays.stream(userIds).map(UserId::new).toList());
  }

  public boolean contains(UserId userId) {
    return value.contains(userId);
  }
}
