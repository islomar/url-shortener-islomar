package com.islomar.yaus.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
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

  @RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
  @ResponseBody
  String findFullUrl(@PathVariable String shortUrlId) {
    return "http://www.osoco.es";
  }

  @RequestMapping(method = RequestMethod.POST)
  @ResponseStatus(value = HttpStatus.CREATED)
  @ResponseBody
  String createShortUrl() {
    return "http://oso.co/000000";
  }

}
