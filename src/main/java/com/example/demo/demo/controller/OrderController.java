package com.example.demo.demo.controller;

import com.example.demo.demo.repository.Order.IOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
  @Autowired
  IOrderRepository orderRepository;

  @GetMapping("/{id}")
  public ResponseEntity<?> getOrderByID(@PathVariable("id") Long id){
    var order = orderRepository.findById(id);
    if (!order.isPresent()){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(order.get(), HttpStatus.OK);
  };
 }
