package org.blog.entity;


public class User {

  private long userId;
  private String userName;
  private String password;
  private String nickname;
  private String sex;
  private long age;
  private String telephone;
  private String email;
  private String image;

  public User(long userId, String userName, String password, String nickname, String sex, long age, String telephone, String email, String image) {
    this.userId = userId;
    this.userName = userName;
    this.password = password;
    this.nickname = nickname;
    this.sex = sex;
    this.age = age;
    this.telephone = telephone;
    this.email = email;
    this.image = image;
  }

  public User(String userName, String password, String nickname, String sex, long age, String telephone, String email) {
    this.userName = userName;
    this.password = password;
    this.nickname = nickname;
    this.sex = sex;
    this.age = age;
    this.telephone = telephone;
    this.email = email;
  }

  public User(String userName, String password) {

    this.userName = userName;
    this.password = password;
  }

  public User() {

  }

  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
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


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }


  public long getAge() {
    return age;
  }

  public void setAge(long age) {
    this.age = age;
  }


  public String getTelephone() {
    return telephone;
  }

  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

}
