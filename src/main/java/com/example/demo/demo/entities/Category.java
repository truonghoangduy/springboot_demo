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
@Table
public class Category extends BaseEntity{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;
  private String description;

  @OneToMany(mappedBy="category")
  @JsonBackReference
  private List<Product> products;

  public Category(Long id, String name, String description,
      List<Product> products) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.products = products;
  }

  public Category() {

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

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
