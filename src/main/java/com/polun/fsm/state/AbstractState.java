package com.polun.fsm.state;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.Trigger;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public abstract class AbstractState<S, E> implements State<S, E> {

  private final List<Trigger<S, E>> triggers;
  @Getter @Setter private FiniteStateMachine<S, E> parent;

  protected AbstractState() {
    this.triggers = new ArrayList<>();
  }

  @Override
  public void handle(Context<S, E> context) {
    triggers.stream().filter(e -> e.canHandle(context)).forEach(e -> e.handle(context));
  }

  @Override
  public boolean canHandle(Context<S, E> context) {
    return triggers.stream().anyMatch(e -> e.canHandle(context));
  }

  @Override
  public final void addTrigger(Trigger<S, E> trigger) {
    this.triggers.add(trigger);
  }
}
