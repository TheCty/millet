package com.millet.pojo;


public class User {

  private String name;
  private String password;
  private int tag;

  public User() {
  }

  public User(String name, String password, int tag) {
    this.name = name;
    this.password = password;
    this.tag = tag;
  }

  public User(String name, String password) {
    this.name = name;
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "User{" +
            "name='" + name + '\'' +
            ", password='" + password + '\'' +
            ", tag=" + tag +
            '}';
  }
}
