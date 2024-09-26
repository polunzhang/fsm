package com.polun.sample.entity.fsm;

public enum Event {
  END,
  START,
  LOGIN,
  LOGOUT,
  TIME_ELAPSED,
  NEW_MESSAGE,
  NEW_POST,
  START_BROADCAST,
  BROADCASTING_SPEAK,
  STOP_BROADCAST,
}
