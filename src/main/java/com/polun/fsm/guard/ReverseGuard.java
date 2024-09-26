package com.polun.fsm.guard;

import com.polun.fsm.context.Context;

public class ReverseGuard<S, E> implements Guard<S, E> {

  private final Guard<S, E> guard;

  public ReverseGuard(Guard<S, E> guard) {
    this.guard = guard;
  }

  @Override
  public boolean test(Context<S, E> context) {
    return !guard.test(context);
  }
}
