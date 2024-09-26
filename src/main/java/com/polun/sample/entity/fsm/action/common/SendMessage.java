package com.polun.sample.entity.fsm.action.common;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class SendMessage implements Action<SampleState, SampleEvent> {

  private final Bot bot;

  private final String[] contents;

  public SendMessage(Bot bot, String... contents) {
    this.bot = bot;
    this.contents = contents;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    bot.sendMessage(contents);
  }
}
