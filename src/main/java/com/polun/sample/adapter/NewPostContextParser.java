package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class NewPostContextParser extends ContextParser<SampleState, SampleEvent> {

  public NewPostContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public String getBracketWord() {
    return "new post";
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.NEW_POST;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new NewPostAdapter();
  }
}
