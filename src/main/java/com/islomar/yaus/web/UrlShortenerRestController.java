package com.islomar.yaus.web;

import com.islomar.yaus.core.InMemoryShortenedUrlRepository;
import com.islomar.yaus.core.ShortenedUrlRepository;
import com.islomar.yaus.core.URLShortenerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletResponse;


@RestController
public class UrlShortenerRestController {

  private final ShortenedUrlRepository shortenedUrlRepository;

  @Autowired
  public UrlShortenerRestController(ShortenedUrlRepository shortenedUrlRepository) {

    this.shortenedUrlRepository = shortenedUrlRepository;
  }

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "URL Shortener up and running!";
  }

  //TODO: return URL instead of String??
  //TODO: exception path (no URL found)
  @RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
  void redirectToFullUrl(@PathVariable String shortUrlId, HttpServletResponse httpServletResponse) throws IOException {

    URLShortenerService urlShortenerService = new URLShortenerService(this.shortenedUrlRepository);
    httpServletResponse.sendRedirect(urlShortenerService.findURLById(shortUrlId));
  }


  @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
  ResponseEntity<String> createShortUrl(@RequestBody String uriStringToBeShortened) {

    //TODO: it should be injected
    URLShortenerService urlShortenerService = new URLShortenerService(this.shortenedUrlRepository);
    try {
      return new ResponseEntity<String>(urlShortenerService.shorten(uriStringToBeShortened).toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException | MalformedURLException ex) {
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

  }

}
