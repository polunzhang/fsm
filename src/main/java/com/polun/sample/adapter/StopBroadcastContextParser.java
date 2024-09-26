package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class StopBroadcastContextParser extends ContextParser<SampleState, SampleEvent> {

  public StopBroadcastContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "stop broadcasting";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.STOP_BROADCAST;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new StopBroadcastAdapter();
  }
}
