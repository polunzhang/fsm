package com.polun.sample.entity.fsm.action.normal;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.forum.Post;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;

public class CommentAndTagAuthor implements Action<SampleState, SampleEvent> {
  private final Bot bot;

  private final String content;

  public CommentAndTagAuthor(Bot bot, String content) {
    this.bot = bot;
    this.content = content;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    context
        .getPayload(Post.class)
        .ifPresent(
            post -> {
              Tags tags = new Tags(List.of(post.getAuthorId()));
              bot.comment(post.getId(), content, tags);
            });
  }
}
