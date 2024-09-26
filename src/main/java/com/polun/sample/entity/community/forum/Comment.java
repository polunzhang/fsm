package com.polun.sample.entity.community.forum;

import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.UserId;

public record Comment(UserId author, String content, Tags tags) {
  public static Comment of(String userId, String content, String... tags) {
    return new Comment(UserId.of(userId), content, Tags.of(tags));
  }
}
