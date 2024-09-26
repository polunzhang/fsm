package com.polun.sample.entity.fsm;

public enum SampleState {
  // Normal state
  NORMAL,
  DEFAULT_CONVERSATION,
  INTERACTING,
  // Record state
  RECORD,
  RECORDING,
  WAITING,
  // KNOWLEDGE_KING state
  KNOWLEDGE_KING,
  THANKS_FOR_JOINING,
  QUESTIONING,
}
