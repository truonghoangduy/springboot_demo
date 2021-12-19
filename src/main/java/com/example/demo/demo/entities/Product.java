package com.example.demo.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Product extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String description;
  private Float price;
  private Long quantity;
  private String imageURL;
  private Float promotion_price;
  @ManyToOne(fetch = FetchType.EAGER,targetEntity = Category.class)
  @JoinColumn(name = "category_id", nullable = false)
  @JsonManagedReference
  @NotNull
  private Category category;

  @OneToMany(mappedBy="product")
  @JsonBackReference
  private List<OrderDetail> orderDetails;

  public Product(Long id, String name, String description, Float price, Long quantity,
      String imageURL, Float promotion_price, Category category,
      List<OrderDetail> orderDetails) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantity = quantity;
    this.imageURL = imageURL;
    this.promotion_price = promotion_price;
    this.category = category;
    this.orderDetails = orderDetails;
  }

  public Product() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public String getImageURL() {
    return imageURL;
  }

  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  public Float getPromotion_price() {
    return promotion_price;
  }

  public void setPromotion_price(Float promotion_price) {
    this.promotion_price = promotion_price;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }
}
