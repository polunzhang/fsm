package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;

public class NewPostContextParser extends ContextParser<SampleState, Event> {

  public NewPostContextParser(ContextParser<SampleState, Event> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "new post";
  }

  @Override
  protected Event getEvent() {
    return Event.NEW_POST;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new NewPostAdapter();
  }
}
