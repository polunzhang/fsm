package com.polun.fsm.context;

import com.polun.fsm.context.Context;
import com.polun.fsm.state.State;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

public class GenericContext<S, E> implements Context<S, E> {

  @Getter private final E event;

  @Setter private State<S, E> currentState;

  private final Object payload;

  public GenericContext(E event, Object payload) {
    this.event = event;
    this.payload = payload;
  }

  @Override
  public <T> Optional<T> getPayload(Class<T> clazz) {
    if (payload == null) {
      return Optional.empty();
    }
    if (clazz.isInstance(payload)) {
      return Optional.of(clazz.cast(payload));
    } else {
      return Optional.empty();
    }
  }
}
