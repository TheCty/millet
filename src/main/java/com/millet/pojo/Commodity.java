package com.millet.pojo;


public class Commodity {

  private int id;
  private String img;
  private String name;
  private Classify classifyId;
  private int count;
  private double price;
  private String context;
  private int tag;

  public Commodity() {
  }

  public Commodity(int id, String img, String name, Classify classifyId, int count, double price, String context, int tag) {
    this.id = id;
    this.img = img;
    this.name = name;
    this.classifyId = classifyId;
    this.count = count;
    this.price = price;
    this.context = context;
    this.tag = tag;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }


  public String getImg() {
    return img;
  }

  public void setImg(String img) {
    this.img = img;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Classify getClassifyId() {
    return classifyId;
  }

  public void setClassifyId(Classify classifyId) {
    this.classifyId = classifyId;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }


  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }


  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }


  public int getTag() {
    return tag;
  }

  public void setTag(int tag) {
    this.tag = tag;
  }

  @Override
  public String toString() {
    return "Commodity{" +
            "id=" + id +
            ", img='" + img + '\'' +
            ", name='" + name + '\'' +
            ", classifyId=" + classifyId +
            ", count=" + count +
            ", price=" + price +
            ", context='" + context + '\'' +
            ", tag=" + tag +
            '}';
  }
}
