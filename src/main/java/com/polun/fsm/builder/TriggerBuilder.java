package com.polun.fsm.builder;

import com.polun.fsm.Trigger;
import com.polun.fsm.action.Action;
import com.polun.fsm.action.CompositeAction;
import com.polun.fsm.guard.CompositeGuard;
import com.polun.fsm.guard.Guard;

public class TriggerBuilder<S, E> {
  private E event;
  private Guard<S, E>[] guards;
  private Action<S, E>[] actions;

  public TriggerBuilder<S, E> event(E event) {
    this.event = event;
    return this;
  }

  @SafeVarargs
  public final TriggerBuilder<S, E> guard(Guard<S, E>... guard) {
    this.guards = guard;
    return this;
  }

  @SafeVarargs
  public final TriggerBuilder<S, E> action(Action<S, E>... action) {
    this.actions = action;
    return this;
  }

  public Trigger<S, E> build() {
    validate();
    Guard<S, E> guard = createGuard();
    Action<S, E> action = createAction();
    return new Trigger<>(event, guard, action);
  }

  private void validate() {
    if (actions == null) {
      throw new IllegalArgumentException("Action cannot be null");
    }
    if (event == null) {
      throw new IllegalArgumentException("Event cannot be null");
    }
  }

  private Guard<S, E> createGuard() {
    if (guards == null || guards.length == 0) {
      return null;
    }
    return guards.length > 1 ? new CompositeGuard<>(guards) : guards[0];
  }

  private Action<S, E> createAction() {
    return actions.length > 1 ? new CompositeAction<>(actions) : actions[0];
  }
}
