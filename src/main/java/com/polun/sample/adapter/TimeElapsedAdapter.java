package com.polun.sample.adapter;

import com.polun.sample.entity.community.TimeElapsed;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeElapsedAdapter extends JsonToObjectAdapter<TimeElapsed> {

  @Override
  protected TimeElapsed convert(String json) {
    Pattern pattern = Pattern.compile(TimeElapsedContextParser.REGEX);
    Matcher matcher = pattern.matcher(json);

    if (matcher.find()) {
      // 提取數字
      String number = matcher.group(1);
      // 提取時間單位
      String timeUnit = matcher.group(2);

      return new TimeElapsed(convertTime(Integer.parseInt(number), timeUnit));
    }

    throw new IllegalArgumentException("Invalid time format: " + json);
  }

  @Override
  public TimeElapsed parse(String json) {
    return convert(json);
  }

  public static long convertTime(long value, String unit) {
    return switch (unit.toLowerCase()) {
      case TimeElapsedContextParser.SECONDS -> TimeUnit.SECONDS.toMillis(value);
      case TimeElapsedContextParser.MINUTES -> TimeUnit.MINUTES.toMillis(value);
      case TimeElapsedContextParser.HOURS -> TimeUnit.HOURS.toMillis(value);
      default -> throw new IllegalArgumentException("Unknown time unit: " + unit);
    };
  }
}
