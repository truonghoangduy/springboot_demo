package com.example.demo.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

  @GetMapping("/")
  public String helloWord() {
    return "product";
  }

}
