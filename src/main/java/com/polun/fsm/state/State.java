package com.polun.fsm.state;

import com.polun.fsm.context.Context;
import com.polun.fsm.context.ContextListener;
import com.polun.fsm.FiniteStateMachine;
import com.polun.fsm.Trigger;

public interface State<S, E> extends ContextListener<S, E> {

  FiniteStateMachine<S, E> getParent();

  void setParent(FiniteStateMachine<S, E> parent);

  S getId();

  void entryAction(Context<S, E> context);

  void exitAction(Context<S, E> context);

  void addTrigger(Trigger<S, E> trigger);
}
