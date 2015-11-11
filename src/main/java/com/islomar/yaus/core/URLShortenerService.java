package com.islomar.yaus.core;


import com.google.common.hash.Hashing;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class URLShortenerService {

  private final ShortenedUrlRepository shortenedUrlRepository;

  //TODO: inject shortener algorithm
  public URLShortenerService(final ShortenedUrlRepository shortenedUrlRepository) {
    this.shortenedUrlRepository = shortenedUrlRepository;
  }

  public URI shorten(String uriStringToBeShortened) throws MalformedURLException {

    URL urlToBeShortened = URI.create(uriStringToBeShortened).toURL();

    URI shortenedURI = generateShortURL(urlToBeShortened);
    shortenedUrlRepository.save(shortenedURI);

    return shortenedURI;
  }

  private URI generateShortURL(URL urlToBeShortened) {
    String shortUrlId = Hashing.murmur3_32().hashString(urlToBeShortened.toString(), StandardCharsets.UTF_8).toString();
    return URI.create("http://oso.co/" + shortUrlId);
  }

  //TODO: what if nothing is found?
  public String findURLById(String shortUrlId) {
    Optional<URI> shortenedURI = shortenedUrlRepository.findByShortenedURI(shortUrlId);
    return shortenedURI.get().toString();
  }
}
