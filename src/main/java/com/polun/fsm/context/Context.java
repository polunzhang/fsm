package com.polun.fsm.context;

import java.util.Optional;

public interface Context<S, E> {
  E getEvent();


  <T> Optional<T> getPayload(Class<T> clazz);
}
