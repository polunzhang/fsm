package com.polun.sample.adapter;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class JsonToObjectAdapter<T> {
  protected final ObjectMapper objectMapper = new ObjectMapper();

  public T parse(String json) {
    return convert(getJsonString(json));
  }

  protected String getJsonString(String input) {
    int jsonStart = input.indexOf('{');
    int jsonEnd = input.lastIndexOf('}');

    if (jsonStart != -1 && jsonEnd != -1) {
      return input.substring(jsonStart, jsonEnd + 1);
    } else {
      return "{}";
    }
  }

  protected abstract T convert(String json);
}
