package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class RecorderGuard implements Guard<SampleState, SampleEvent> {

  private final Bot bot;

  public RecorderGuard(Bot bot) {
    this.bot = bot;
  }

  @Override
  public boolean test(Context<SampleState, SampleEvent> context) {
    return context.getPayload(Message.class).stream()
        .anyMatch(message -> message.authorId().equals(bot.getRecords().getRecorder()));
  }
}
