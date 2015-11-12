package com.islomar.yaus.core.infrastructure.repositories;

import com.islomar.yaus.core.model.ShortenedUrlRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public final class InMemoryShortenedUrlRepository implements ShortenedUrlRepository {

  private final static Logger LOG = LoggerFactory.getLogger(InMemoryShortenedUrlRepository.class);

  private Map<String, URL> shortenedUriStore = new ConcurrentHashMap<>();

  @Override
  public void save(String shortenedId, URL fullUrl) {
    shortenedUriStore.put(shortenedId, fullUrl);
    LOG.debug("Entry [{}, {}] successfully saved", shortenedId, fullUrl);
  }

  @Override
  public Optional<URL> findURLById(String shortenedId) {
    return Optional.ofNullable(shortenedUriStore.get(shortenedId));
  }
}
