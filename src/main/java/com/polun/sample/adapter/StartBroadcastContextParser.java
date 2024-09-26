package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class StartBroadcastContextParser extends ContextParser<SampleState, SampleEvent> {

  public StartBroadcastContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "go broadcasting";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.START_BROADCAST;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new StartBroadcastAdapter();
  }

}
