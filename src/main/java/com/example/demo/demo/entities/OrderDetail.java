package com.example.demo.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Table
@Entity
public class OrderDetail extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Long quantity;
  private Float price;

  @ManyToOne(fetch = FetchType.EAGER,targetEntity = Order.class)
  @JoinColumn(name = "order_id", nullable = false)
  @JsonManagedReference
  @NotNull
  private Order order;

  @ManyToOne(fetch = FetchType.EAGER,targetEntity = Product.class)
  @JoinColumn(name = "product_id", nullable = false)
  @JsonManagedReference
  @NotNull
  private Product product;

  public OrderDetail(Long id, Long quantity, Float price, Order order,
      Product product) {
    this.id = id;
    this.quantity = quantity;
    this.price = price;
    this.order = order;
    this.product = product;
  }

  public OrderDetail() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getQuantity() {
    return quantity;
  }

  public void setQuantity(Long quantity) {
    this.quantity = quantity;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
