package com.polun.sample.adapter;

import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;
import java.util.regex.Pattern;

public class TimeElapsedContextParser extends ContextParser<SampleState, SampleEvent> {
  public static final String SECONDS = "seconds";
  public static final String MINUTES = "minutes";
  public static final String HOURS = "hours";

  public static final String REGEX =
      String.format("\\[(\\d+)\\s+(%s|%s|%s)\\s+elapsed\\]", SECONDS, MINUTES, HOURS);
  public static final Pattern PATTERN = Pattern.compile(REGEX);

  public TimeElapsedContextParser(ContextParser<SampleState, SampleEvent> next) {
    super(next);
  }

  @Override
  public boolean match(String input) {
    return PATTERN.matcher(input).matches();
  }

  @Override
  public String getBracketWord() {
    throw new UnsupportedOperationException();
  }

  @Override
  protected SampleEvent getEvent() {
    return SampleEvent.TIME_ELAPSED;
  }

  @Override
  protected JsonToObjectAdapter<?> getJsonToPayloadObjectAdapter() {
    return new TimeElapsedAdapter();
  }
}
