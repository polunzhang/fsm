package com.polun.sample.entity.fsm.action.record;

import com.polun.fsm.context.Context;
import com.polun.fsm.action.Action;
import com.polun.sample.entity.bot.Bot;
import com.polun.sample.entity.bot.Records;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.broadcast.Speak;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;

public class ReplayRecords implements Action<SampleState, SampleEvent> {
  private final Bot bot;

  public ReplayRecords(Bot bot) {
    this.bot = bot;
  }

  @Override
  public void run(Context<SampleState, SampleEvent> context) {
    Records records = bot.getRecords();
    String[] content = records.getReplay().stream().map(Speak::context).toArray(String[]::new);
    if (content.length != 0) {
      content[0] = String.format("[Record Replay] %s", content[0]);
      UserId recorder = records.getRecorder();
      bot.sendMessage(new Tags(List.of(recorder)), content);
    }
  }
}
