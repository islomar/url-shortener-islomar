package com.islomar.yaus.core;

import java.net.URI;
import java.util.Optional;

public interface ShortenedUrlRepository {

  void save(URI uri);

  Optional<URI> findByShortenedURI(URI shortenedURI);
}
