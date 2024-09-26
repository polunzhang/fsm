package com.polun.sample.entity.community.broadcast;

import com.polun.sample.entity.community.UserId;
import java.util.Optional;

public class Broadcast {
  private Broadcasting broadcasting;

  public boolean isBroadcasting() {
    return broadcasting != null;
  }

  public void goBroadcasting(UserId speakerId) {
    if (isBroadcasting()) {
      this.stopBroadCasting(speakerId);
    }
    this.broadcasting = new Broadcasting(speakerId);
  }

  public void send(Speak speak) {
    if (!isBroadcasting()) {
      throw new IllegalStateException("Not broadcasting");
    }
    broadcasting.send(speak);
  }

  public void stopBroadCasting(UserId speakerId) {
    if (!isBroadcasting()) {
      return;
    }
    if (speakerId.equals(broadcasting.getSpeaker())) {
      this.broadcasting = null;
    }
  }

  public Optional<Broadcasting> getBroadcasting() {
    return Optional.ofNullable(broadcasting);
  }
}
