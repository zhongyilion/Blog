package org.blog.entity;


public class User {

  private long id;
  private String username;
  private String password;
  private String nickname;
  private String headerimg;

  public User(long id, String username, String password, String nickname, String headerimg) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.headerimg = headerimg;
  }

  public User(String username, String password, String nickname) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
  }

  public User() {

  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getHeaderimg() {
    return headerimg;
  }

  public void setHeaderimg(String headerimg) {
    this.headerimg = headerimg;
  }

}
