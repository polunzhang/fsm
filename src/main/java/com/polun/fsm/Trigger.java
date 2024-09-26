package com.polun.fsm;

import com.polun.fsm.action.Action;
import com.polun.fsm.context.Context;
import com.polun.fsm.context.ContextListener;
import com.polun.fsm.guard.Guard;
import lombok.Getter;

@Getter
public class Trigger<S, E> implements ContextListener<S, E> {
  private final E event;
  private final Guard<S, E> guard;
  private final Action<S, E> action;

  public Trigger(E event, Guard<S, E> guard, Action<S, E> action) {
    this.event = event;
    this.guard = guard;
    this.action = action;
  }

  @Override
  public void handle(Context<S, E> context) {
    action.run(context);
  }

  @Override
  public boolean canHandle(Context<S, E> context) {
    return compare(event, context.getEvent()) && (guard == null || guard.test(context));
  }

  public boolean compare(E event1, E event2) {
    if (event1 instanceof Enum) {
      return event1 == event2;
    }

    if (isPrimitiveOrWrapper(event1)) {
      return event1.equals(event2);
    }

    if (event1 != null) {
      return event1.getClass().equals(event2.getClass());
    }

    return false;
  }

  private boolean isPrimitiveOrWrapper(Object obj) {
    return obj instanceof Byte
        || obj instanceof Short
        || obj instanceof Integer
        || obj instanceof Long
        || obj instanceof Float
        || obj instanceof Double
        || obj instanceof Boolean
        || obj instanceof Character;
  }
}
