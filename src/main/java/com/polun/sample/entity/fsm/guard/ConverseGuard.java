package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class ConverseGuard implements Guard<SampleState, SampleEvent> {

  private final Bot bot;

  public ConverseGuard(Bot bot) {
    this.bot = bot;
  }

  @Override
  public boolean test(Context<SampleState, SampleEvent> context) {
    return context.getPayload(Message.class).stream()
        .noneMatch(message -> message.authorId().equals(bot.getUser().getUserId()));
  }
}
