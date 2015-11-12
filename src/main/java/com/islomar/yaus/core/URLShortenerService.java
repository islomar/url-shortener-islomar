package com.islomar.yaus.core;


import com.google.common.hash.Hashing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class URLShortenerService {

  private final ShortenedUrlRepository shortenedUrlRepository;
  private final ShortenerAlgorithm shortenerAlgorithm;

  @Autowired
  public URLShortenerService(final ShortenedUrlRepository shortenedUrlRepository, ShortenerAlgorithm shortenerAlgorithm) {
    this.shortenedUrlRepository = shortenedUrlRepository;
    this.shortenerAlgorithm = shortenerAlgorithm;
  }

  //TODO: refactor, URI vs URL vs String
  //TODO: wher to validate URL
  public String shorten(String urlStringToBeShortened) throws MalformedURLException {

    URL urlToBeShortened = URI.create(urlStringToBeShortened).toURL();

    String shortenedUrlId = this.shortenerAlgorithm.shorten(urlToBeShortened.toString());
    shortenedUrlRepository.save(shortenedUrlId, urlToBeShortened);

    return shortenedUrlId;
  }

  public Optional<URL> findURLById(String shortUrlId) {
    return shortenedUrlRepository.findByShortenedURI(shortUrlId);
  }
}
