package com.islomar.yaus.core;


import com.google.common.hash.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class URLShortenerService {

  private static final URI URL_SHORTENER_BASE_URL = URI.create("http://oso.co/");

  private final ShortenedUrlRepository shortenedUrlRepository;

  //TODO: inject shortener algorithm
  public URLShortenerService(final ShortenedUrlRepository shortenedUrlRepository) {
    this.shortenedUrlRepository = shortenedUrlRepository;
  }

  //TODO: refactor, URI vs URL vs String
  //TODO: wher to validate URL
  public URI shorten(String urlStringToBeShortened) throws MalformedURLException {

    URL urlToBeShortened = URI.create(urlStringToBeShortened).toURL();

    String shortenedUrlId = generateShortURL(urlToBeShortened);
    shortenedUrlRepository.save(shortenedUrlId, urlToBeShortened);

    return URI.create(URL_SHORTENER_BASE_URL + shortenedUrlId);
  }

  private String generateShortURL(URL urlToBeShortened) {
    return Hashing.murmur3_32().hashString(urlToBeShortened.toString(), StandardCharsets.UTF_8).toString();
  }

  //TODO: what if nothing is found?
  public String findURLById(String shortUrlId) {
    Optional<URL> shortenedURI = shortenedUrlRepository.findByShortenedURI(shortUrlId);
    return shortenedURI.get().toString();
  }
}
