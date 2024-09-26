package com.polun.sample.entity.fsm.action.normal;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;

public class Converse implements Action<SampleState, Event> {
  private final Bot bot;
  private final String[] contents;

  private int index = 0;

  public Converse(Bot bot, String... contents) {
    this.bot = bot;
    this.contents = contents;
  }

  @Override
  public void run(Context<SampleState, Event> context) {
    context
        .getPayload(Message.class)
        .ifPresent(
            message -> {
              Tags tags = new Tags(List.of(message.authorId()));
              bot.sendMessage(tags, contents[index++ % contents.length]);
            });
  }
}
