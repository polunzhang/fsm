package com.polun.sample.adapter;

import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.forum.Content;
import com.polun.sample.entity.community.forum.Post;
import com.polun.sample.entity.community.forum.PostId;

public class NewPostAdapter extends JsonToObjectAdapter<Post> {
  @Override
  protected Post convert(String json) {
    try {
      Data data = super.objectMapper.readValue(json, Data.class);
      return new Post(
          PostId.of(data.id),
          UserId.of(data.authorId),
          data.title,
          Content.of(data.content),
          Tags.of(data.tags));
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to convert JSON to Message: " + e.getMessage(), e);
    }
  }

  private record Data(String id, String authorId, String title, String content, String[] tags) {}
}
