package com.polun.sample.entity.community;

import com.polun.sample.adapter.ContextParser;
import com.polun.sample.entity.community.broadcast.Broadcast;
import com.polun.sample.entity.community.broadcast.Speak;
import com.polun.sample.entity.community.chatroom.ChatRoom;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.community.forum.Comment;
import com.polun.sample.entity.community.forum.Forum;
import com.polun.sample.entity.community.forum.Post;
import com.polun.sample.entity.community.forum.PostId;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import com.polun.sample.utils.LogHelper;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Community {

  private final ChatRoom chatroom;
  private final Forum forum;
  private final Broadcast broadcast;

  @Getter
  private final List<User> onlineUsers;

  @Getter(AccessLevel.NONE)
  private final List<ContextObserver<SampleState, Event>> observers;

  @Getter(AccessLevel.NONE)
  @Setter
  private ContextParser<SampleState, Event> contextParser;

  public Community(ChatRoom chatroom, Forum forum, Broadcast broadcast) {
    this.chatroom = chatroom;
    this.forum = forum;
    this.broadcast = broadcast;
    this.observers = new ArrayList<>();
    this.onlineUsers = new ArrayList<>();
  }

  public void submit(String input) {

    contextParser
        .handle(input)
        .ifPresent(
            c -> {
              switch (c.getEvent()) {
                case LOGIN -> c.getPayload(User.class).ifPresent(this::login);
                case LOGOUT -> c.getPayload(UserId.class).ifPresent(this::logout);
                case TIME_ELAPSED -> c.getPayload(TimeElapsed.class).ifPresent(this::send);
                case NEW_MESSAGE -> c.getPayload(Message.class).ifPresent(this::send);
                case START_BROADCAST -> c.getPayload(UserId.class).ifPresent(this::goBroadcasting);
                case BROADCASTING_SPEAK -> c.getPayload(Speak.class).ifPresent(this::send);
                case STOP_BROADCAST -> c.getPayload(UserId.class).ifPresent(this::stopBroadCasting);
                case NEW_POST -> c.getPayload(Post.class).ifPresent(this::send);
                default -> {
                  // do nothing
                }
              }
              observers.forEach(observer -> observer.update(c));
            });
  }

  private void stopBroadCasting(UserId speakerId) {
    LogHelper.log(LogHelper.formatStopBroadCasting(speakerId));
    broadcast.stopBroadCasting(speakerId);
  }

  public void login(User user) {
    logout(user.getUserId());
    onlineUsers.add(user);
  }

  public void logout(UserId userId) {
    onlineUsers.removeIf(e -> e.getUserId().equals(userId));
  }

  public void goBroadcasting(UserId speakerId) {
    LogHelper.log(LogHelper.formatStartBroadcast(speakerId));
    broadcast.goBroadcasting(speakerId);
  }

  public void send(Speak speak) {
    LogHelper.log(LogHelper.formatSpeak(speak));
    broadcast.send(speak);
  }

  public void send(Message message) {
    LogHelper.log(LogHelper.formatMessage(message));
    chatroom.send(message);
  }

  public void send(TimeElapsed elapsedMillis) {
    LogHelper.log(LogHelper.formatTimeElapsed(elapsedMillis));
  }

  public void send(Post post) {
    LogHelper.log(LogHelper.formatPost(post));
    forum.send(post);
  }

  public void comment(PostId postId, Comment comment) {
    LogHelper.log(LogHelper.formatComment(postId, comment));
    forum.comment(postId, comment);
  }

  public User getUser(UserId userId) {
    return onlineUsers.stream()
        .filter(user -> user.getUserId().equals(userId))
        .findFirst()
        .orElseThrow();
  }

  public int getOnlineUserCount() {
    return onlineUsers.size();
  }

  public void subscribe(ContextObserver<SampleState, Event> observer) {
    observers.add(observer);
  }
}
