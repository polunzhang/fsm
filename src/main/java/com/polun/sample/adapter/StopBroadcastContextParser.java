package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class StopBroadcastContextParser extends ContextParser<SampleState, Event> {

  public StopBroadcastContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "stop broadcasting";
  }

  @Override
  protected Event getEvent() {
    return Event.STOP_BROADCAST;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new StopBroadcastAdapter();
  }
}
