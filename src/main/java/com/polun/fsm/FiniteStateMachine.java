package com.polun.fsm;

import com.polun.fsm.context.Context;
import com.polun.fsm.context.ContextListener;
import com.polun.fsm.state.State;

public interface FiniteStateMachine<S, E> extends ContextListener<S, E> {

  void enterState(S id);

  void enterState(S id, Context<S, E> context);

  State<S, E> getState(S id);

  State<S, E> getCurrentState();

  State<S, E> getInitialState();
}
