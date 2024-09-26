package com.polun.sample.entity.community.forum;

import java.util.ArrayList;
import java.util.List;

public class Forum {

  private final List<Post> posts;

  public Forum() {
    this.posts = new ArrayList<>();
  }

  public void send(Post post) {
    posts.add(post);
  }

  public void comment(PostId postId, Comment comment) {
    posts.stream()
        .filter(e -> e.getId().equals(postId))
        .findFirst()
        .orElseThrow()
        .addComment(comment);
  }
}
