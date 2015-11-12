package com.islomar.yaus.core;


import com.google.common.hash.Hashing;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class URLShortenerService {

  private static final URI URL_SHORTENER_BASE_URL = URI.create("http://oso.co/");

  private final ShortenedUrlRepository shortenedUrlRepository;
  private final ShortenerAlgorithm shortenerAlgorithm;

  public URLShortenerService(final ShortenedUrlRepository shortenedUrlRepository, ShortenerAlgorithm shortenerAlgorithm) {
    this.shortenedUrlRepository = shortenedUrlRepository;
    this.shortenerAlgorithm = shortenerAlgorithm;
  }

  //TODO: refactor, URI vs URL vs String
  //TODO: wher to validate URL
  public URI shorten(String urlStringToBeShortened) throws MalformedURLException {

    URL urlToBeShortened = URI.create(urlStringToBeShortened).toURL();

    String shortenedUrlId = this.shortenerAlgorithm.shorten(urlToBeShortened.toString());
    shortenedUrlRepository.save(shortenedUrlId, urlToBeShortened);

    return URI.create(URL_SHORTENER_BASE_URL + shortenedUrlId);
  }

  public Optional<URL> findURLById(String shortUrlId) {
    return shortenedUrlRepository.findByShortenedURI(shortUrlId);
  }
}
