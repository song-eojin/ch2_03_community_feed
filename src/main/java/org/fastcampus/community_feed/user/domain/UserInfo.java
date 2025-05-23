package org.fastcampus.community_feed.user.domain;

// VO 에서 유효성 검증
public class UserInfo {
  private final String name;
  private final String profileImageUrl;

  public UserInfo(String name, String profileImageUrl) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("이름은 빈 값이 입력될 수 없습니다.");
    }

    this.name = name;
    this.profileImageUrl = profileImageUrl;
  }
}
