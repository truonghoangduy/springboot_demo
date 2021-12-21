package com.example.demo.demo.controller;

import com.example.demo.demo.entities.Order;
import com.example.demo.demo.repository.Order.IOrderRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {
  @Autowired
  IOrderRepository orderRepository;

  @GetMapping("/{id}")
  public ResponseEntity getOrderById(@PathVariable Long id){
    Optional<Order> order = orderRepository.findById(id);
    if(!order.isPresent()){
      return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(order, HttpStatus.OK);
  }

  @GetMapping("/{phone}")
  public ResponseEntity getOrderByPhone(@PathVariable String phone){
    Optional<Order> order = orderRepository.findOrderByPhone(phone);
    if(!order.isPresent()){
      return new ResponseEntity("Not found", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(order, HttpStatus.OK);
  }
}
