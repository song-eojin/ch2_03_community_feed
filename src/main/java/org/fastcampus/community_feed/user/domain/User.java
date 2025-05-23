package org.fastcampus.community_feed.user.domain;

import java.util.Objects;

public class User {
  private final Long id;
  private final UserInfo userInfo;
  private final UserRelationCount followingCount; // 내가 팔로우하고 있는 카운트
  private final UserRelationCount followerCount; // 나한테 얼마나 팔로워가 있는지 카운트

  public User(Long id, UserInfo userInfo) {
    if (userInfo == null) {
      throw new IllegalArgumentException("UserInfo cannot be null");
    }

    this.id = id;
    this.userInfo = userInfo;
    this.followingCount = new UserRelationCount();
    this.followerCount = new UserRelationCount();
  }

  public void follow(User targetUser) {
    if (this.equals(targetUser)) {
      throw new IllegalArgumentException("");
    }

    followingCount.increaseCount();
    targetUser.increaseFollowerCount();
  }

  public void unfollow(User targetUser) {
    if (this.equals(targetUser)) {
      throw new IllegalArgumentException("");
    }

    followingCount.decreaseCount();
    targetUser.decreaseFollowerCount();
  }

  private void increaseFollowerCount() {
    followerCount.increaseCount();
  }

  private void decreaseFollowerCount() {
    followerCount.decreaseCount();
  }

  public int getFollowingCount() {
    return followingCount.getCount();
  }

  public int getFollowerCount() {
    return followerCount.getCount();
  }

  public Long getId() {
    return id;
  }

  public UserInfo getUserInfo() {
    return userInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
