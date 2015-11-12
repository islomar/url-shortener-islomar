package com.islomar.yaus.core.model;

import java.net.URL;
import java.util.Optional;

public interface ShortenedUrlRepository {

  void save(String shortenedId, URL absoluteUrl);

  Optional<URL> findURLById(String shortenedId);
}
