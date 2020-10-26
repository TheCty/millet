package com.millet.pojo;


public class Orderform {

  private String name;
  private User userName;
  private Address addressId;
  private String commodityIds;
  private String counts;
  private int tag;

  public Orderform() {
  }

  public Orderform(String name, User userName, Address addressId, String commodityIds, String counts, int tag) {
    this.name = name;
    this.userName = userName;
    this.addressId = addressId;
    this.commodityIds = commodityIds;
    this.counts = counts;
    this.tag = tag;
  }

  public Address getAddressId() {
    return addressId;
  }

  public void setAddressId(Address addressId) {
    this.addressId = addressId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public User getUserName() {
    return userName;
  }

  public void setUserName(User userName) {
    this.userName = userName;
  }

  public String getCommodityIds() {
    return commodityIds;
  }

  public void setCommodityIds(String commodityIds) {
    this.commodityIds = commodityIds;
  }


  public String getCounts() {
    return counts;
  }

  public void setCounts(String counts) {
    this.counts = counts;
  }


  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "Orderform{" +
            "name='" + name + '\'' +
            ", userName=" + userName +
            ", addressId=" + addressId +
            ", commodityIds='" + commodityIds + '\'' +
            ", counts='" + counts + '\'' +
            ", tag=" + tag +
            '}';
  }
}
