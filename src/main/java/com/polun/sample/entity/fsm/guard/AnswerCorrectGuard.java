package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class AnswerCorrectGuard implements Guard<SampleState, Event> {

  private final Bot bot;

  public AnswerCorrectGuard(Bot bot) {
    this.bot = bot;
  }

  @Override
  public boolean test(Context<SampleState, Event> context) {
    return context.getPayload(Message.class).stream()
        .anyMatch(message -> bot.getQuestionGroup().isCorrect(message.content().charAt(0)));
  }
}
