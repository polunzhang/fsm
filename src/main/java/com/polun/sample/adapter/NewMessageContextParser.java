package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class NewMessageContextParser extends ContextParser<SampleState, SampleEvent> {

  public NewMessageContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "new message";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.NEW_MESSAGE;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new NewMessageAdapter();
  }
}
