package com.polun.sample.entity.community.broadcast;

import com.polun.sample.entity.community.UserId;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Broadcasting {

  private final UserId speaker;

  private final List<Speak> speaks;

  public Broadcasting(UserId speaker) {
    this.speaker = speaker;
    this.speaks = new ArrayList<>();
  }

  public void send(Speak speak) {
    if (!speaker.equals(speak.speakId())) {
      throw new IllegalArgumentException("only speaker can send speak.");
    }
    speaks.add(speak);
  }
}
