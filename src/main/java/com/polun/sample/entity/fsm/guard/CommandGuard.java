package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.Command;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class CommandGuard implements Guard<SampleState, Event> {

  private final Command command;

  public CommandGuard(Command command) {
    this.command = command;
  }

  @Override
  public boolean test(Context<SampleState, Event> context) {
    return context.getPayload(Message.class).stream()
        .anyMatch(
            message -> message.content().equals(command.getValue()) && command.isQuotaEnough());
  }
}
