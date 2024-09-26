package com.polun.sample.entity.fsm.action.normal;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.User;
import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.forum.Post;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;

public class CommentAndTagAllOnlineUsers implements Action<SampleState, SampleEvent> {

  private final Bot bot;
  private final String comment;

  public CommentAndTagAllOnlineUsers(Bot bot, String comment) {
    this.bot = bot;
    this.comment = comment;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {

    context
        .getPayload(Post.class)
        .ifPresent(
            post -> {
              List<UserId> onlineUsers =
                  bot.getCommunity().getOnlineUsers().stream().map(User::getUserId).toList();
              Tags tags = new Tags(onlineUsers);
              bot.comment(post.getId(), comment, tags);
            });
  }
}
