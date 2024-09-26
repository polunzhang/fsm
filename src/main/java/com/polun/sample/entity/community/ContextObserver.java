package com.polun.sample.entity.community;

import com.polun.fsm.context.Context;

public interface ContextObserver<S, E> {
  void update(Context<S, E> event);
}
