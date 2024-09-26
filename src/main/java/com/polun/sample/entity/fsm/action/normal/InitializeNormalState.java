package com.polun.sample.entity.fsm.action.normal;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.action.Action;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import lombok.Setter;

public class InitializeNormalState implements Action<SampleState, Event> {

  private final Guard<SampleState, Event> guard;

  @Setter private FiniteStateMachine<SampleState, Event> fsm;

  public InitializeNormalState(Guard<SampleState, Event> guard) {
    this.guard = guard;
  }

  @Override
  public void run(Context<SampleState, Event> context) {
    if (guard.test(context)) {
      fsm.enterState(SampleState.INTERACTING);
    } else {
      fsm.enterState(SampleState.DEFAULT_CONVERSATION);
    }
  }
}
