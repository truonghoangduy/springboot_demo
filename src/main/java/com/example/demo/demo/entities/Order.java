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
@Table(name="orders")
public class Order extends BaseEntity{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private Float total;
  private String status;
  private String ship_address;
  private String phone;
  @ManyToOne(fetch = FetchType.EAGER,targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  @JsonManagedReference
  @NotNull
  private User user;


  @OneToMany(mappedBy="order")
  //@JsonBackReference
  private List<OrderDetail> orderDetails;

  public Order(Long id, Float total, String status, String ship_address, String phone,
      User user, List<OrderDetail> orderDetails) {
    this.id = id;
    this.total = total;
    this.status = status;
    this.ship_address = ship_address;
    this.phone = phone;
    this.user = user;
    this.orderDetails = orderDetails;
  }

  public Order() {

  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Float getTotal() {
    return total;
  }

  public void setTotal(Float total) {
    this.total = total;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getShip_address() {
    return ship_address;
  }

  public void setShip_address(String ship_address) {
    this.ship_address = ship_address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public List<OrderDetail> getOrderDetails() {
    return orderDetails;
  }

  public void setOrderDetails(List<OrderDetail> orderDetails) {
    this.orderDetails = orderDetails;
  }
}
