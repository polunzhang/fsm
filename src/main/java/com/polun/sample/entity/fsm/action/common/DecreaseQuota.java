package com.polun.sample.entity.fsm.action.common;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.fsm.Command;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class DecreaseQuota implements Action<SampleState, SampleEvent> {

  private final Command command;

  public DecreaseQuota(Command command) {
    this.command = command;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    command.decreaseQuota();
  }
}
