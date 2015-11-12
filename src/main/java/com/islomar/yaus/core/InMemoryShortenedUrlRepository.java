package com.islomar.yaus.core;

import org.springframework.stereotype.Repository;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryShortenedUrlRepository implements ShortenedUrlRepository {

  private Map<String, URL> shortenedUriStore = new ConcurrentHashMap<>();

  @Override
  public void save(String shortenedId, URL fullUrl) {
    shortenedUriStore.put(shortenedId, fullUrl);
  }

  @Override
  public Optional<URL> findByShortenedURI(String shortenedId) {
    return Optional.ofNullable(shortenedUriStore.get(shortenedId));
  }
}
