package com.islomar.yaus.core;

import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;

public interface ShortenedUrlRepository {

  void save(String shortenedId, URL fullUri);

  Optional<URL> findByShortenedURI(String shortenedId);
}
