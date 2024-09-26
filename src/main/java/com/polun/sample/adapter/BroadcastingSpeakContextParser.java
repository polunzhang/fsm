package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class BroadcastingSpeakContextParser extends ContextParser<SampleState, SampleEvent> {

  public BroadcastingSpeakContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "speak";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.BROADCASTING_SPEAK;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new BroadcastingSpeakAdapter();
  }
}
