package com.polun.sample.entity.fsm.action.king;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.bot.QuestionGroup;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class AskQuestion implements Action<SampleState, SampleEvent> {

  private final Bot bot;

  public AskQuestion(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    QuestionGroup questionGroup = bot.getQuestionGroup();
    if (!questionGroup.allAnswered()) {
      bot.sendMessage(questionGroup.getQuestionDescriptions());
    }
  }
}
