package com.millet.pojo;


public class Shopping {

  private int id;
  private User userName;
  private String commodityIds;
  private String counts;

  public Shopping() {
  }

  public Shopping(int id, User userName, String commodityIds, String counts) {
    this.id = id;
    this.userName = userName;
    this.commodityIds = commodityIds;
    this.counts = counts;
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
    if(Integer.parseInt(counts)>0){
        this.counts =counts;

    }
  }

  @Override
  public String toString() {
    return "Shopping{" +
            "id=" + id +
            ", userName=" + userName +
            ", commodityIds='" + commodityIds + '\'' +
            ", counts='" + counts + '\'' +
            '}';
  }
}
