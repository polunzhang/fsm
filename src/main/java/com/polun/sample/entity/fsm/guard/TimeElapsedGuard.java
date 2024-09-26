package com.polun.sample.entity.fsm.guard;

import com.polun.fsm.context.Context;
import com.polun.fsm.guard.Guard;
import com.polun.sample.entity.community.TimeElapsed;
import com.polun.sample.entity.fsm.SampleEvent;
import com.polun.sample.entity.fsm.SampleState;

public class TimeElapsedGuard implements Guard<SampleState, SampleEvent> {

  private final long milliseconds;

  private long elapsedTime = 0;

  public TimeElapsedGuard(long milliseconds) {
    if (milliseconds < 0) {
      throw new IllegalArgumentException("Milliseconds must be positive.");
    }
    this.milliseconds = milliseconds;
  }

  @Override
  public boolean test(Context<SampleState, SampleEvent> context) {
    elapsedTime += context.getPayload(TimeElapsed.class).orElseThrow().millis();
    return milliseconds <= elapsedTime;
  }
}
