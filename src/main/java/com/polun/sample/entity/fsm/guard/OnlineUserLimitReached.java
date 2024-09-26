package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class OnlineUserLimitReached implements Guard<SampleState, SampleEvent> {

  private final Bot bot;
  private final int limit;

  public OnlineUserLimitReached(Bot bot, int limit) {
    this.bot = bot;
    this.limit = limit;
  }

  @Override
  public boolean test(Context<SampleState, SampleEvent> context) {
    return bot.getCommunity().getOnlineUserCount() >= limit;
  }
}
