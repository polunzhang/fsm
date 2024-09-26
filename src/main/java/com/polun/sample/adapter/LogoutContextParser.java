package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class LogoutContextParser extends ContextParser<SampleState, Event> {

  public LogoutContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "logout";
  }

  @Override
  protected Event getEvent() {
    return Event.LOGOUT;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new LogoutAdapter();
  }

}
