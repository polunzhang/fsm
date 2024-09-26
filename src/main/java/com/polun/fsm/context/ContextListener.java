package com.polun.fsm.context;

public interface ContextListener<S, E> {

  void handle(Context<S, E> context);

  boolean canHandle(Context<S, E> context);
}
