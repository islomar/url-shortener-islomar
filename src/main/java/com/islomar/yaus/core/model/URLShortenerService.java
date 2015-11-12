package com.islomar.yaus.core.model;


import com.islomar.yaus.core.infrastructure.services.IdGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Optional;

@Service
public final class URLShortenerService {

  private final ShortenedUrlRepository shortenedUrlRepository;
  private final IdGenerator idGenerator;

  @Autowired
  public URLShortenerService(final ShortenedUrlRepository shortenedUrlRepository, IdGenerator idGenerator) {
    this.shortenedUrlRepository = shortenedUrlRepository;
    this.idGenerator = idGenerator;
  }

  public String shorten(String urlStringToBeShortened) throws MalformedURLException {

    URL urlToBeShortened = URI.create(urlStringToBeShortened).toURL();

    String shortenedUrlId = this.idGenerator.generateIdFrom(urlToBeShortened.toString());

    shortenedUrlRepository.save(shortenedUrlId, urlToBeShortened);

    return shortenedUrlId;
  }

  public Optional<URL> findURLById(String shortUrlId) {
    return shortenedUrlRepository.findURLById(shortUrlId);
  }
}
