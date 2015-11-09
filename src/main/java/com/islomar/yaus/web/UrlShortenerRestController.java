package com.islomar.yaus.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UrlShortenerRestController {

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "Hello World, here I am!";
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @ResponseStatus(value = HttpStatus.CREATED)
  String shortenUrl() {
    return "http://oso.co/000000";
  }

}
