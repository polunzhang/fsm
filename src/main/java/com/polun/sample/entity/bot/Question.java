package com.polun.sample.entity.bot;

import java.util.Collection;
import java.util.Objects;
import lombok.Getter;

@Getter
public class Question {

  private final String value;
  private final Collection<Option> options;
  private final char answer;

  public Question(String value, Collection<Option> options, char answer) {
    this.value = value;
    this.options = options;
    if (options.stream().noneMatch(e -> Objects.equals(e.label(), answer))) {
      throw new IllegalArgumentException("Answer must be one of the options.");
    }
    this.answer = answer;
  }

  public boolean isCorrect(char input) {
    return input == answer;
  }
}
