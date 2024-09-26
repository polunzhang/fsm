package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class BroadcastingSpeakContextParser extends ContextParser<SampleState, Event> {

  public BroadcastingSpeakContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "speak";
  }

  @Override
  protected Event getEvent() {
    return Event.BROADCASTING_SPEAK;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new BroadcastingSpeakAdapter();
  }
}
