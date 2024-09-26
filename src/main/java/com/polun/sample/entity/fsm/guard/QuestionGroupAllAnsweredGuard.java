package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class QuestionGroupAllAnsweredGuard implements Guard<SampleState, SampleEvent> {

  private final Bot bot;

  public QuestionGroupAllAnsweredGuard(Bot bot) {
    this.bot = bot;
  }

  @Override
  public boolean test(Context<SampleState, SampleEvent> context) {
    return bot.getQuestionGroup().allAnswered();
  }
}
