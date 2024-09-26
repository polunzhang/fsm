package com.polun.fsm_plugin;

import com.polun.fsm.context.Context;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.builder.FiniteStateMachineBuilder;
import com.polun.fsm.action.Action;
import com.polun.fsm.state.AbstractState;
import com.polun.fsm.state.State;
import com.polun.fsm.state.Transition;
import java.util.List;
import java.util.Optional;
import lombok.Setter;

public class CompositeState<S, E> extends AbstractState<S, E> implements FiniteStateMachine<S, E> {

  private final S id;
  private final FiniteStateMachine<S, E> subFsm;
  private final Action<S, E> entryAction;
  private final Action<S, E> exitAction;

  @Setter private FiniteStateMachine<S, E> parent;

  @SuppressWarnings("unchecked")
  protected CompositeState(
      S id,
      Action<S, E> entryAction,
      Action<S, E> exitAction,
      S initState,
      List<State<S, E>> states,
      List<Transition<S, E>> transitions) {
    super();
    this.id = id;
    this.entryAction = entryAction;
    this.exitAction = exitAction;
    this.subFsm =
        new FiniteStateMachineBuilder<S, E>()
            .initialState(initState)
            .states(states.toArray(State[]::new))
            .transitions(transitions.toArray(Transition[]::new))
            .build();
  }

  @Override
  public FiniteStateMachine<S, E> getParent() {
    return parent;
  }

  @Override
  public S getId() {
    return id;
  }

  @Override
  public void entryAction(Context<S, E> context) {
    Optional.ofNullable(entryAction).ifPresent(a -> a.run(context));
    subFsm.getCurrentState().entryAction(context);
  }

  @Override
  public void exitAction(Context<S, E> context) {
    Optional.ofNullable(exitAction).ifPresent(a -> a.run(context));
    subFsm.getCurrentState().exitAction(context);
  }

  @Override
  public void enterState(S id) {
    subFsm.enterState(id);
  }

  @Override
  public void enterState(S id, Context<S, E> context) {
    subFsm.enterState(id, context);
  }

  @Override
  public State<S, E> getState(S id) {
    return subFsm.getState(id);
  }

  @Override
  public State<S, E> getCurrentState() {
    return subFsm.getCurrentState();
  }

  @Override
  public State<S, E> getInitialState() {
    return subFsm.getInitialState();
  }

  @Override
  public void handle(Context<S, E> context) {
    subFsm.handle(context);
    super.handle(context);
  }

  @Override
  public boolean canHandle(Context<S, E> context) {
    return super.canHandle(context) || subFsm.canHandle(context);
  }
}
