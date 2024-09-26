package com.polun.sample.entity.fsm.action.record;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.broadcast.Speak;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class DoRecording implements Action<SampleState, SampleEvent> {

  private final Bot bot;

  public DoRecording(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    context.getPayload(Speak.class).ifPresent(speak -> bot.getRecords().doRecording(speak));
  }
}
