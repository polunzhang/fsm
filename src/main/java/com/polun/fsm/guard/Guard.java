package com.polun.fsm.guard;

import com.polun.fsm.context.Context;

@FunctionalInterface
public interface Guard<S, E> {

  boolean test(Context<S, E> context);
}
