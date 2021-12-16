package com.example.demo.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DemoController {

  @GetMapping("/")
  @ResponseBody
  public String helloWord() {
    return "Hello world";
  }

  @PostMapping("/")
  public String helloWordzzz() {
    return "Hello world";
  }


}
