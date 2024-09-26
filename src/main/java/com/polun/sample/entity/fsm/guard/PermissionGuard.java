package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.User;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class PermissionGuard implements Guard<SampleState, SampleEvent> {

  private final Bot bot;

  private final Boolean isAdmin;

  public PermissionGuard(Bot bot, Boolean isAdmin) {
    this.bot = bot;
    this.isAdmin = isAdmin;
  }

  @Override
  public boolean test(Context<SampleState, SampleEvent> context) {

    if (isAdmin == null) return true;

    return context.getPayload(Message.class).stream()
        .anyMatch(
            message -> {
              User user = bot.getCommunity().getUser(message.authorId());
              return isAdmin.equals(user.isAdmin());
            });
  }
}
