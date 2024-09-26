package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class LogoutContextParser extends ContextParser<SampleState, SampleEvent> {

  public LogoutContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "logout";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.LOGOUT;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new LogoutAdapter();
  }

}
