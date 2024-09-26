package com.polun.fsm.guard;

import com.polun.fsm.context.Context;
import java.util.List;
import lombok.Getter;

public class CompositeGuard<S,E> implements Guard<S,E> {

  @Getter
  private final List<Guard<S,E>> guards;

  @SafeVarargs
  public CompositeGuard(Guard<S,E>... guards) {
    this.guards = List.of(guards);
  }

  @Override
  public boolean test(Context<S,E> context) {
    return guards.stream().allMatch(e -> e.test(context));
  }
}
