package com.polun.sample.entity.fsm.action.record;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class StartRecording implements Action<SampleState, Event> {

  private final Bot bot;

  public StartRecording(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, Event> context) {
    context
        .getPayload(Message.class)
        .ifPresent(message -> bot.getRecords().startRecording(message.authorId()));
  }
}
