package com.polun.sample.entity.fsm.action.record;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import lombok.Setter;

public class InitializeRecordState implements Action<SampleState, SampleEvent> {

  private final Bot bot;
  @Setter private FiniteStateMachine<SampleState, SampleEvent> fsm;

  public InitializeRecordState(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    if (bot.getCommunity().getBroadcast().isBroadcasting()) {
      fsm.enterState(SampleState.RECORDING);
    } else {
      fsm.enterState(SampleState.WAITING);
    }
  }
}
