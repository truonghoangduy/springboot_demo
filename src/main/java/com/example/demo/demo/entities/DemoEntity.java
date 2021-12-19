package com.example.demo.demo.entities;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DemoEntity {

  @Id
  private Long id;

  private String description;
}
