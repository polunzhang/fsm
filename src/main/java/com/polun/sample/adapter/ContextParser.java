package com.polun.sample.adapter;

import com.polun.fsm.context.Context;
import com.polun.fsm.context.ContextBuilder;
import java.util.Optional;

public abstract class ContextParser<S, E> {

  private final ContextParser<S, E> next;

  protected ContextParser(ContextParser<S, E> next) {
    this.next = next;
  }

  public Optional<Context<S, E>> handle(String input) {
    if (match(input)) {
      return Optional.of(parse(input));
    } else if (next != null) {
      return next.handle(input);
    }
    return Optional.empty();
  }

  protected boolean match(String input) {
    return getBracketWord().equals(getBracketWord(input));
  }

  protected abstract String getBracketWord();

  protected abstract E getEvent();

  protected String getBracketWord(String input) {
    int jsonStart = input.indexOf('[');
    int jsonEnd = input.indexOf(']');

    if (jsonStart != -1 && jsonEnd != -1) {
      return input.substring(jsonStart + 1, jsonEnd);
    } else {
      throw new IllegalArgumentException("Invalid JSON string: " + input);
    }
  }

  protected Context<S, E> parse(String input) {
    Object payload = getJsonToPayloadObjectAdapter().parse(input);
    return new ContextBuilder<S, E>().withEvent(getEvent()).withPayload(payload).build();
  }

  protected abstract JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter();
}
