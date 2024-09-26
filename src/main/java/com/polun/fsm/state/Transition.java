package com.polun.fsm.state;

import com.polun.fsm.action.Action;
import com.polun.fsm.guard.Guard;
import lombok.Builder;

@Builder
public record Transition<S, E>(
    S source, S target, E event, Guard<S, E> guard, Action<S, E> action) {}
