package com.islomar.yaus.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerRestController {

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "Hello World, here I am!";
  }
}
