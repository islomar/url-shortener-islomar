package com.islomar.yaus.web;

import com.islomar.yaus.core.URLShortenerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
public class UrlShortenerRestController {

  private final static Logger LOG = LoggerFactory.getLogger(UrlShortenerRestController.class);

  private final URLShortenerService urlShortenerService;

  @Autowired
  public UrlShortenerRestController(URLShortenerService urlShortenerService) {

    this.urlShortenerService = urlShortenerService;
  }

  @RequestMapping("/")
  @ResponseBody
  String home() {
    return "URL Shortener up and running!";
  }

  @RequestMapping(value = "/{shortUrlId}", method = RequestMethod.GET)
  void redirectToFullUrl(@PathVariable String shortUrlId, HttpServletResponse response) throws IOException {

    LOG.debug("Received request to redirect to '%s'", shortUrlId);
    Optional<URL> shortenedURLFound = urlShortenerService.findURLById(shortUrlId);
    if (shortenedURLFound.isPresent()) {
      response.sendRedirect(shortenedURLFound.get().toString());
    } else {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.TEXT_PLAIN_VALUE)
  ResponseEntity<String> createShortUrl(@RequestBody String uriStringToBeShortened, HttpServletRequest request) {

    LOG.debug("Received request to shorten the URL '%s'", uriStringToBeShortened);
    try {
      String shortenedUrlId = urlShortenerService.shorten(uriStringToBeShortened);
      String shortenedUrl = request.getRequestURL() + shortenedUrlId;
      return new ResponseEntity<String>(shortenedUrl, HttpStatus.CREATED);
    } catch (IllegalArgumentException | MalformedURLException ex) {
      LOG.error("Error when trying to create a shortened URL for %s: %s", uriStringToBeShortened, ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
    }

  }

}
