package org.fastcampus.community_feed.post.domain;

import org.fastcampus.community_feed.post.domain.content.Content;
import org.fastcampus.community_feed.post.domain.like.LikeCounter;
import org.fastcampus.community_feed.user.domain.User;

public class Post {
  private final Long id;
  private final User author;
  private final Content content;
  private PostPublicationState state;
  private LikeCounter likeCounter;

  public Post(Long id, User author, Content content, PostPublicationState state, LikeCounter likeCounter) {
    if (author == null) {
      throw new IllegalArgumentException("작성자는 존재하지 않을 수 없습니다.");
    }
    if (content == null) {
      throw new IllegalArgumentException("내용이 비었거나 존재하지 않을 수 없습니다.");
    }

    this.id = id;
    this.author = author;
    this.content = content;
    this.state = state;
    this.likeCounter = likeCounter;
  }

  public Post(Long id, User author, Content content) {
    this(id, author, content, PostPublicationState.PUBLIC, new LikeCounter());
  }

  public void updateContent(User user, String content) {
    if (!author.equals(user)) {
      throw new IllegalArgumentException("오직 작성자만 내용을 수정할 수 있습니다.");
    }
    this.content.updateContent(content);
  }

  public void like(User user) {
    if (author.equals(user)) {
      throw new IllegalArgumentException("작성자는 자신의 게시글에 좋아요를 누를 수 없습니다.");
    }
    likeCounter.increase();
  }

  public void unlike() {
    likeCounter.decrease();
  }

  public void updateState(PostPublicationState state) {
    this.state = state;
  }

  public PostPublicationState getState() {
    return state;
  }

  public Long getId() {
    return id;
  }

  public User getAuthor() {
    return author;
  }

  public Content getContent() {
    return content;
  }

  public int getLikeCount() {
    return likeCounter.getLikeCount();
  }
}
