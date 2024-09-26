package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class LoginContextParser extends ContextParser<SampleState, Event> {

  public LoginContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "login";
  }

  @Override
  protected Event getEvent() {
    return Event.LOGIN;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new LoginAdapter();
  }
}
