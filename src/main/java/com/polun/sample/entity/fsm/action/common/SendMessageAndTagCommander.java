package com.polun.sample.entity.fsm.action.common;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;

public class SendMessageAndTagCommander implements Action<SampleState, SampleEvent> {

  private final Bot bot;
  private final String content;

  public SendMessageAndTagCommander(Bot bot, String content) {
    this.bot = bot;
    this.content = content;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    context
        .getPayload(Message.class)
        .ifPresent(
            message -> {
              Tags tags = new Tags(List.of(message.authorId()));
              bot.sendMessage(tags, this.content);
            });
  }
}
