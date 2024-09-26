package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class StartBroadcastContextParser extends ContextParser<SampleState, Event> {

  public StartBroadcastContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "go broadcasting";
  }

  @Override
  protected Event getEvent() {
    return Event.START_BROADCAST;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new StartBroadcastAdapter();
  }

}
