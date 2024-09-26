package com.polun.fsm.action;

import com.polun.fsm.context.Context;
import java.util.List;

public class CompositeAction<S, E> implements Action<S, E> {

  private final List<Action<S, E>> actions;

  @SafeVarargs
  public CompositeAction(Action<S, E>... actions) {
    this.actions = List.of(actions);
  }

  @Override
  public void run(Context<S, E> context) {
    actions.forEach(action -> action.run(context));
  }
}
