package com.islomar.yaus.web;

import com.islomar.yaus.core.ShortenedUrlRepository;
import com.islomar.yaus.core.ShortenerAlgorithm;
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
import java.net.URL;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;


@RestController
public class UrlShortenerRestController {

  private final ShortenedUrlRepository shortenedUrlRepository;
  private final ShortenerAlgorithm shortenerAlgorithm;

  @Autowired
  public UrlShortenerRestController(ShortenedUrlRepository shortenedUrlRepository, ShortenerAlgorithm shortenerAlgorithm) {

    this.shortenedUrlRepository = shortenedUrlRepository;
    this.shortenerAlgorithm = shortenerAlgorithm;
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

    URLShortenerService urlShortenerService = new URLShortenerService(this.shortenedUrlRepository, shortenerAlgorithm);
    Optional<URL> shortenedURLFound = urlShortenerService.findURLById(shortUrlId);
    if (shortenedURLFound.isPresent()) {
      httpServletResponse.sendRedirect(shortenedURLFound.get().toString());
    } else {
      httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
  ResponseEntity<String> createShortUrl(@RequestBody String uriStringToBeShortened) {

    //TODO: it should be injected
    URLShortenerService urlShortenerService = new URLShortenerService(this.shortenedUrlRepository, shortenerAlgorithm);
    try {
      return new ResponseEntity<String>(urlShortenerService.shorten(uriStringToBeShortened).toString(), HttpStatus.CREATED);
    } catch (IllegalArgumentException | MalformedURLException ex) {
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

  }

}
