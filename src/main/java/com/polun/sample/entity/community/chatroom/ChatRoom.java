package com.polun.sample.entity.community.chatroom;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ChatRoom {

  private final List<Message> messages;

  public ChatRoom() {
    this.messages = new ArrayList<>();
  }

  public void send(Message message) {
    messages.add(message);
  }
}
