package com.polun.sample.entity.fsm.action.common;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.action.Action;

public class EnterState<S, E> implements Action<S, E> {

  private final S target;

  private final FiniteStateMachine<S, E> fsm;

  public EnterState(FiniteStateMachine<S, E> fsm, S target) {
    this.target = target;
    this.fsm = fsm;
  }

  @Override
  public void run(Context<S, E> context) {
    fsm.enterState(target, context);
  }
}
