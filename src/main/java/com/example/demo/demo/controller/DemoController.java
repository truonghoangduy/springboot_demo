package com.example.demo.demo.controller;


import com.example.demo.demo.repository.Product.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.model.IModel;

@Controller
public class DemoController {

  @GetMapping("/hello")
  public String hello(
      @RequestParam(name = "name", required = false, defaultValue = "") String name,
      Model model
  ) {
    model.addAttribute("name", name);

    return "hello";
  }

  @GetMapping("/hello/{date}")
  public String helloPathVariable(
      @PathVariable("date") String date,
      @RequestParam(name = "name", required = false, defaultValue = "") String name,
      Model model
  ) {
    model.addAttribute("date", date);
    model.addAttribute("name", name);
    return "hello";
  }

  @Autowired
  IProductRepository productRepository;

  @GetMapping("/")
  public String helloWord(Model model) {
    var listofProduct = productRepository.findAll();
    model.addAttribute("listOfProduct",listofProduct);
//    listofProduct.get(0).getCategory().getName()
    return "product";
  }

}
