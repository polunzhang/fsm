package com.polun.fsm;

import com.polun.fsm.state.State;
import com.polun.fsm.state.Transition;
import java.util.List;

public class GenericFiniteStateMachine<S, E> extends AbstractFiniteStateMachine<S, E> {

  protected GenericFiniteStateMachine(
      S initialState, List<State<S, E>> states, List<Transition<S, E>> transitions) {
    super(initialState, states, transitions);
  }
}
