package com.polun.sample.entity.community.forum;

import java.util.Arrays;
import java.util.List;

public record Content(List<String> lines) {
  public static Content of(String... lines) {
    return new Content(Arrays.stream(lines).toList());
  }
}
