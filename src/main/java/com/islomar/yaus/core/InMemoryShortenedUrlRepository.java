package com.islomar.yaus.core;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryShortenedUrlRepository implements ShortenedUrlRepository {

  //TODO - concurrency!!!
  private List<URI> listOfURLs = new ArrayList<URI>();

  @Override
  public void save(URI shortenedUri) {
    listOfURLs.add(shortenedUri);
  }

  @Override
  public Optional<URI> findByShortenedURI(String shortenedId) {
    throw new NotImplementedException();
  }
}
