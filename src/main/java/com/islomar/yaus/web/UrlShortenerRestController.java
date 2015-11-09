package com.islomar.yaus.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UrlShortenerRestController {

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "URL Shortener up and running!";
  }

  @RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
  @ResponseBody
  String findFullUrl(@PathVariable String shortUrlId) {
    return "http://www.osoco.es";
  }

  @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(value = HttpStatus.CREATED)
  @ResponseBody
  String createShortUrl(@RequestBody String uriToBeShortened) {
    return "http://oso.co/000000";
  }

}
