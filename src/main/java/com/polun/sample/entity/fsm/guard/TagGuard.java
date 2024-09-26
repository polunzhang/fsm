package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class TagGuard implements Guard<SampleState, Event> {

  private final UserId target;

  public TagGuard(UserId target) {
    this.target = target;
  }

  @Override
  public boolean test(Context<SampleState, Event> context) {
    return context.getPayload(Message.class).stream()
        .anyMatch(message -> message.tags().contains(target));
  }
}
