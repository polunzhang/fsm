package com.polun.fsm.builder;

import com.polun.fsm.AbstractFiniteStateMachine;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.state.State;
import com.polun.fsm.state.Transition;
import java.util.Arrays;
import java.util.List;

public class FiniteStateMachineBuilder<S, E> {
  private S initialState;
  private List<State<S, E>> states;
  private List<Transition<S, E>> transitions;

  @SafeVarargs
  public final FiniteStateMachineBuilder<S, E> transitions(Transition<S, E>... transitions) {
    this.transitions = Arrays.stream(transitions).toList();
    return this;
  }

  public FiniteStateMachineBuilder<S, E> initialState(S initialState) {
    this.initialState = initialState;
    return this;
  }

  @SafeVarargs
  public final FiniteStateMachineBuilder<S, E> states(State<S, E>... states) {
    this.states = Arrays.stream(states).toList();
    return this;
  }

  public FiniteStateMachine<S, E> build() {
    return new AbstractFiniteStateMachine<>(initialState, states, transitions) {};
  }
}
