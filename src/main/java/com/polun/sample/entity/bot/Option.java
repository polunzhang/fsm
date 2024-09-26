package com.polun.sample.entity.bot;

public record Option(String value, char label, String description) {
  public static Option of(String desc) {
    return new Option(desc, desc.charAt(0), desc.substring(desc.indexOf(") ")));
  }
}
