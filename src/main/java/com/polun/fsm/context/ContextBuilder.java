package com.polun.fsm.context;

public class ContextBuilder<S, E> {
  private E event;
  private Object payload;

  public ContextBuilder<S, E> withEvent(E event) {
    this.event = event;
    return this;
  }

  public ContextBuilder<S, E> withPayload(Object payload) {
    this.payload = payload;
    return this;
  }

  public Context<S, E> build() {
    return new GenericContext<>(event, payload);
  }
}
