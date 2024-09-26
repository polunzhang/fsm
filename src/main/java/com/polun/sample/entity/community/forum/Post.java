package com.polun.sample.entity.community.forum;

import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.UserId;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Post {
  private final PostId id;
  private final UserId authorId;
  private final String title;
  private final Content content;
  private final Tags tags;
  private final List<Comment> comments;

  public Post(PostId id, UserId authorId, String title, Content content, Tags tags) {
    this.id = id;
    this.authorId = authorId;
    this.title = title;
    this.content = content;
    this.tags = tags;
    this.comments = new ArrayList<>();
  }

  public void addComment(Comment comment) {
    comments.add(comment);
  }
}
