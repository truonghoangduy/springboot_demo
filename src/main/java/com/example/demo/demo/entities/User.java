package com.example.demo.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String password;
  @OneToMany(mappedBy="user")
  //@JsonBackReference
  private List<Order> orders;

  public User(Long id, String name, String password,
      List<Order> orders) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.orders = orders;
  }

  public User() {

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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<Order> getOrders() {
    return orders;
  }

  public void setOrders(List<Order> orders) {
    this.orders = orders;
  }
}
