package com.stocklookup.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
  @GetMapping("/")
  public String home() {
    return "welcome to the TheStocker api";
  }

  @GetMapping("/v1/thestocker/about")
  public String about() {
    return "Its an hanlak Product";
  }
}
