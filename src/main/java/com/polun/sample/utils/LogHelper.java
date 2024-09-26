package com.polun.sample.utils;

import com.polun.sample.adapter.TimeElapsedContextParser;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.TimeElapsed;
import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.broadcast.Speak;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.community.forum.Comment;
import com.polun.sample.entity.community.forum.Content;
import com.polun.sample.entity.community.forum.Post;
import com.polun.sample.entity.community.forum.PostId;

public class LogHelper {

  private LogHelper() {
    // Private constructor to prevent instantiation of this class.
  }

  public static void log(String message) {
    // Mock implementation, just print the message to console
    System.out.println(message);
  }

  public static String formatTimeElapsed(TimeElapsed timeElapsed) {
    long millis = timeElapsed.millis();

    long seconds = millis / 1000;
    long minutes = seconds / 60;
    long hours = minutes / 60;

    int n;
    String timeUnit;
    if (hours > 0) {
      n = (int) hours;
      timeUnit = TimeElapsedContextParser.HOURS;
    } else if (minutes > 0) {
      n = (int) minutes;
      timeUnit = TimeElapsedContextParser.MINUTES;
    } else {
      n = (int) seconds;
      timeUnit = TimeElapsedContextParser.SECONDS;
    }

    return String.format("🕑 %d %s elapsed...", n, timeUnit);
  }

  public static String formatSpeak(Speak speak) {
    UserId userId = speak.speakId();
    return userId.isBot()
        ? String.format("🤖 speaking: %s", speak.context())
        : String.format("📢 %s: %s", userId.value(), speak.context());
  }

  public static String formatPost(Post post) {
    return String.format(
        "%s: 【%s】%s %s",
        post.getAuthorId().value(),
        post.getTitle(),
        formatContent(post.getContent()),
        formatTags(post.getTags()));
  }

  private static String formatContent(Content content) {
    return String.join(System.lineSeparator(), content.lines());
  }

  private static String formatTags(Tags tags) {
    return String.join(
        ", ", tags.value().stream().map(UserId::value).map(e -> String.format("@%s", e)).toList());
  }

  public static String formatComment(PostId postId, Comment comment) {
    return String.format(
        "🤖 comment in post %s: %s %s",
        postId.value(), comment.content(), formatTags(comment.tags()));
  }

  public static String formatStartBroadcast(UserId speakerId) {
    return speakerId.isBot()
        ? "🤖 go broadcasting..."
        : String.format("📢 %s is broadcasting...", speakerId.value());
  }

  public static String formatMessage(Message message) {
    UserId authorId = message.authorId();
    return authorId.isBot()
        ? String.format("🤖: %s %s", message.content(), formatTags(message.tags()))
        : String.format(
            "💬 %s: %s %s", authorId.value(), message.content(), formatTags(message.tags()));
  }

  public static String formatStopBroadCasting(UserId speakerId) {
    return speakerId.isBot()
        ? "🤖 stop broadcasting..."
        : String.format("📢 %s stop broadcasting", speakerId.value());
  }
}
