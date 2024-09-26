package com.polun.sample.entity.fsm;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FSMObserver implements
    com.polun.sample.entity.community.ContextObserver<SampleState, Event> {
  private final FiniteStateMachine<SampleState, Event> fsm;

  @Override
  public void update(Context<SampleState, Event> event) {
  }
}
