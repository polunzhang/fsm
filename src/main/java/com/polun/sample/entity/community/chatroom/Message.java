package com.polun.sample.entity.community.chatroom;

import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.UserId;

public record Message(UserId authorId, String content, Tags tags) {
  public static Message of(String authorId, String content, String[] tags) {
    return new Message(UserId.of(authorId), content, Tags.of(tags));
  }
}
