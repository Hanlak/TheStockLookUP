package com.stocklookup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
  @GetMapping("/")
  public String home(Model model) {
    model.addAttribute("error", "Click below to get All suggestions");
    model.addAttribute("link", "/v1/thestocker/getter/getallsuggestions");
    model.addAttribute("home", "home");
    return "index";
  }

  @GetMapping("/v1/thestocker/about")
  public String about() {
    return "Its an hanlak Product";
  }
}
