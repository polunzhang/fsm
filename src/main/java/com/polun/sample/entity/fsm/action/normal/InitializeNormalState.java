package com.polun.sample.entity.fsm.action.normal;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.action.Action;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import lombok.Setter;

public class InitializeNormalState implements Action<SampleState, SampleEvent> {

  private final Guard<SampleState, SampleEvent> guard;

  @Setter private FiniteStateMachine<SampleState, SampleEvent> fsm;

  public InitializeNormalState(Guard<SampleState, SampleEvent> guard) {
    this.guard = guard;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    if (guard.test(context)) {
      fsm.enterState(SampleState.INTERACTING);
    } else {
      fsm.enterState(SampleState.DEFAULT_CONVERSATION);
    }
  }
}
