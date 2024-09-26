package com.polun.fsm.state;

import com.polun.fsm.action.Action;
import com.polun.fsm.context.Context;
import com.polun.fsm.state.AbstractState;
import java.util.Optional;
import lombok.Builder;
import lombok.Getter;

@Builder
public class GenericState<S, E> extends AbstractState<S, E> {

  @Getter private final S id;
  private final Action<S, E> entryAction;
  private final Action<S, E> exitAction;

  public GenericState(S id, Action<S, E> entryAction, Action<S, E> exitAction) {
    this.id = id;
    this.entryAction = entryAction;
    this.exitAction = exitAction;
  }

  @Override
  public void entryAction(Context<S, E> context) {
    Optional.ofNullable(entryAction).ifPresent(a -> a.run(context));
  }

  @Override
  public void exitAction(Context<S, E> context) {
    Optional.ofNullable(exitAction).ifPresent(a -> a.run(context));
  }
}
