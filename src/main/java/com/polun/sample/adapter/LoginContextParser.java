package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class LoginContextParser extends ContextParser<SampleState, SampleEvent> {

  public LoginContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "login";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.LOGIN;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new LoginAdapter();
  }
}
