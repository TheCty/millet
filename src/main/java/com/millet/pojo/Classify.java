package com.millet.pojo;


public class Classify {

  private int id;
  private String name;
  private int tag;

  public Classify() {
  }

  public Classify(int id, String name, int tag) {
    this.id = id;
    this.name = name;
    this.tag = tag;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "Classify{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", tag=" + tag +
            '}';
  }
}
