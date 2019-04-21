package org.blog.entity;


import java.sql.Date;

public class Article {

  private long articleId;
  private String time;
  private String title;
  private String image;
  private long dianzan;
  private long pinlum;
  private String content;
//  private long userId;
  private User user;

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Article(String time, String title, String image, long dianzan, long pinlum, String content, User user) {
    this.time = time;
    this.title = title;
    this.image = image;
    this.dianzan = dianzan;
    this.pinlum = pinlum;
    this.content = content;
    this.user = user;
  }

  public Article() {
  }



  public long getArticleId() {
    return articleId;
  }

  public void setArticleId(long articleId) {
    this.articleId = articleId;
  }


  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }


  public long getDianzan() {
    return dianzan;
  }

  public void setDianzan(long dianzan) {
    this.dianzan = dianzan;
  }


  public long getPinlum() {
    return pinlum;
  }

  public void setPinlum(long pinlum) {
    this.pinlum = pinlum;
  }


  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }



}
