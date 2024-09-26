package com.polun.fsm;

import com.polun.fsm.action.CompositeAction;
import com.polun.fsm.context.Context;
import com.polun.fsm.guard.CompositeGuard;
import com.polun.fsm.state.State;
import com.polun.fsm.state.Transition;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;

public abstract class AbstractFiniteStateMachine<S, E> implements FiniteStateMachine<S, E> {
  @Getter private final State<S, E> initialState;
  private final Map<S, State<S, E>> stateMap;
  private final List<Trigger<S, E>> transitionTriggers;
  @Getter private State<S, E> currentState;

  protected AbstractFiniteStateMachine(
      S initialState, List<State<S, E>> states, List<Transition<S, E>> transitions) {
    this.stateMap = new HashMap<>();
    states.forEach(s -> stateMap.put(s.getId(), s));
    this.initialState = stateMap.get(initialState);
    this.currentState = this.initialState;
    states.forEach(s -> s.setParent(this));
    this.transitionTriggers = transitions.stream().map(this::buildTransitionTrigger).toList();
  }

  private Trigger<S, E> buildTransitionTrigger(Transition<S, E> transition) {
    return new Trigger<>(
        transition.event(),
        new CompositeGuard<>(
            // current state should be equal to source state of the transition
            context -> this.getCurrentState().getId().equals(transition.source()),
            // should pass the guard of the transition
            context -> transition.guard() == null || (transition.guard().test(context))),
        new CompositeAction<>(
            // run the action of the transition
            context -> Optional.ofNullable(transition.action()).ifPresent(a -> a.run(context)),
            // transit to the target state
            context -> this.enterState(transition.target(), context)));
  }

  @Override
  public void handle(Context<S, E> context) {
    getCurrentState().handle(context);
    this.transitionTriggers.stream()
        .filter(e -> e.canHandle(context))
        .findFirst()
        .ifPresent(e -> e.handle(context));
  }

  @Override
  public State<S, E> getState(S id) {

    State<S, E> state = stateMap.get(id);

    return Optional.of(state)
        .orElseThrow(
            () -> new IllegalArgumentException("State not found: " + getCurrentState() + id));
  }

  @Override
  public boolean canHandle(Context<S, E> event) {
    return getCurrentState().canHandle(event)
        || this.transitionTriggers.stream().anyMatch(e -> e.canHandle(event));
  }

  @Override
  public void enterState(S id) {
    this.currentState = getState(id);
  }

  @Override
  public void enterState(S id, Context<S, E> context) {
    this.currentState.exitAction(context);
    enterState(id);
    this.currentState.entryAction(context);
  }
}
