package com.millet.pojo;


public class Address {

  private int id;
  private User userName;
  private String name;
  private String phone;
  private String address;

  public Address() {
  }

  public Address(int id, User userName, String name, String phone, String address) {
    this.id = id;
    this.userName = userName;
    this.name = name;
    this.phone = phone;
    this.address = address;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public User getUserName() {
    return userName;
  }

  public void setUserName(User userName) {
    this.userName = userName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Address{" +
            "id=" + id +
            ", userName=" + userName +
            ", name='" + name + '\'' +
            ", phone='" + phone + '\'' +
            ", address='" + address + '\'' +
            '}';
  }
}
