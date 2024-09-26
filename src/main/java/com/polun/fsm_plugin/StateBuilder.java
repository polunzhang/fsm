package com.polun.fsm_plugin;

import com.polun.fsm.state.GenericState;
import com.polun.fsm.action.Action;
import com.polun.fsm.state.State;
import com.polun.fsm.state.Transition;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StateBuilder<S, E> {
  private S id;
  private Action<S, E> entryAction;
  private Action<S, E> exitAction;

  private S initialState;
  private List<State<S, E>> states = new ArrayList<>();
  private List<Transition<S, E>> transitions = new ArrayList<>();

  public StateBuilder<S, E> initialState(S initialState) {
    this.initialState = initialState;
    return this;
  }

  @SuppressWarnings("unchecked")
  public StateBuilder<S, E> states(State<S, E>... states) {
    this.states = Arrays.stream(states).toList();
    return this;
  }

  @SuppressWarnings("unchecked")
  public StateBuilder<S, E> transitions(Transition<S, E>... transitions) {
    this.transitions = Arrays.stream(transitions).toList();
    return this;
  }

  public StateBuilder<S, E> id(S id) {
    this.id = id;
    return this;
  }

  public StateBuilder<S, E> entryAction(Action<S, E> entryAction) {
    this.entryAction = entryAction;
    return this;
  }

  public StateBuilder<S, E> exitAction(Action<S, E> exitAction) {
    this.exitAction = exitAction;
    return this;
  }

  public State<S, E> build() {
    if (id == null) {
      throw new IllegalArgumentException("State cannot be null");
    }
    return GenericState.<S, E>builder()
        .id(id)
        .entryAction(entryAction)
        .exitAction(exitAction)
        .build();
  }

  public CompositeState<S, E> buildCompositeState() {
    if (id == null) {
      throw new IllegalArgumentException("State cannot be null");
    }

    if (initialState == null || states.isEmpty()) {
      throw new IllegalArgumentException("Initial state and states cannot be null or empty");
    }
    return new CompositeState<>(id, entryAction, exitAction, initialState, states, transitions);
  }
}
