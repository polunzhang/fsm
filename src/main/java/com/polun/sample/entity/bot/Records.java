package com.polun.sample.entity.bot;

import com.polun.sample.entity.community.UserId;
import com.polun.sample.entity.community.broadcast.Speak;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class Records {
  private final List<Speak> speaks;
  private UserId recorder;

  public Records() {
    speaks = new ArrayList<>();
  }

  public void startRecording(UserId recorder) {
    this.recorder = recorder;
  }

  public void doRecording(Speak speak) {
    speaks.add(speak);
  }

  public List<Speak> getReplay() {
    var replay = new ArrayList<>(speaks);
    speaks.clear();
    return replay;
  }
}
