package com.polun.fsm.action;


import com.polun.fsm.context.Context;

@FunctionalInterface
public interface Action<S,E> {
  void run(Context<S,E> context);
}
