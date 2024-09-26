package com.polun.sample.entity.community;

import lombok.Getter;

@Getter
public class User {
  public static final User BOT = new User(UserId.BOT, true);
  private final UserId userId;
  private final boolean isAdmin;

  public User(UserId userId, boolean isAdmin) {
    this.userId = userId;
    this.isAdmin = isAdmin;
  }

  public User(String userId, boolean isAdmin) {
    this.userId = UserId.of(userId);
    this.isAdmin = isAdmin;
  }
}
