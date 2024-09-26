package com.polun.sample.entity.fsm.action.king;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;

public class RecordQuestioningScore implements Action<SampleState, Event> {
  private final Bot bot;

  public RecordQuestioningScore(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, Event> context) {
    context
        .getPayload(Message.class)
        .ifPresent(
            e -> {
              UserId userId = e.authorId();
              bot.sendMessage(new Tags(List.of(userId)), "Congrats! you got the answer!");
              bot.getQuestionGroup().recordScore(userId);
            });
  }
}
