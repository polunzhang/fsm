package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class NewMessageContextParser extends ContextParser<SampleState, Event> {

  public NewMessageContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "new message";
  }

  @Override
  protected Event getEvent() {
    return Event.NEW_MESSAGE;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new NewMessageAdapter();
  }
}
