package com.polun.sample.entity.bot;

import com.polun.fsm.FiniteStateMachine;
import com.polun.sample.entity.community.Community;
import com.polun.sample.entity.community.Tags;
import com.polun.sample.entity.community.User;
import com.polun.sample.entity.community.broadcast.Speak;
import com.polun.sample.entity.community.chatroom.Message;
import com.polun.sample.entity.community.forum.Comment;
import com.polun.sample.entity.community.forum.PostId;
import com.polun.sample.entity.fsm.Event;
import com.polun.sample.entity.fsm.SampleState;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Bot {

  private final QuestionGroup questionGroup;

  private final Records records;

  private Community community;

  @Setter private FiniteStateMachine<SampleState, Event> fsm;

  public Bot() {
    this.records = new Records();
    this.questionGroup =
        new QuestionGroup(
            List.of(
                new Question(
                    "請問哪個 SQL 語句用於選擇所有的行？",
                    List.of(
                        Option.of("A) SELECT *"),
                        Option.of("B) SELECT ALL"),
                        Option.of("C) SELECT ROWS"),
                        Option.of("D) SELECT DATA")),
                    'A'),
                new Question(
                    "請問哪個 CSS 屬性可用於設置文字的顏色？",
                    List.of(
                        Option.of("A) text-align"),
                        Option.of("B) font-size"),
                        Option.of("C) color"),
                        Option.of("D) padding")),
                    'C'),
                new Question(
                    "請問在計算機科學中，「XML」代表什麼？",
                    List.of(
                        Option.of("A) Extensible Markup Language"),
                        Option.of("B) Extensible Modeling Language"),
                        Option.of("C) Extended Markup Language"),
                        Option.of("D) Extended Modeling Language")),
                    'A')));
  }

  public void login(Community community) {
    this.community = community;
    community.login(this.getUser());
  }

  public User getUser() {
    return User.BOT;
  }

  public void sendMessage(String... content) {
    sendMessage(Tags.of(null), content);
  }

  public void sendSpeak(String content) {
    getCommunity().send(new Speak(getUser().getUserId(), content));
  }

  public void sendMessage(Tags tags, String... content) {
    community.send(
        new Message(getUser().getUserId(), String.join(System.lineSeparator(), content), tags));
  }

  public void comment(PostId postId, String content, Tags tags) {
    community.comment(postId, new Comment(getUser().getUserId(), content, tags));
  }
}
