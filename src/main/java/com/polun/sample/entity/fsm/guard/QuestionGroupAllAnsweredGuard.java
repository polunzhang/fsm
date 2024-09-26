package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class QuestionGroupAllAnsweredGuard implements Guard<SampleState, Event> {

  private final Bot bot;

  public QuestionGroupAllAnsweredGuard(Bot bot) {
    this.bot = bot;
  }

  @Override
  public boolean test(Context<SampleState, Event> context) {
    return bot.getQuestionGroup().allAnswered();
  }
}
