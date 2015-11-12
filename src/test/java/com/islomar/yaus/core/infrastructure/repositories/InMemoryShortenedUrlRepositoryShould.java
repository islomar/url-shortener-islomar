package com.islomar.yaus.core.infrastructure.repositories;

import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URI;

import static java.lang.Boolean.FALSE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class InMemoryShortenedUrlRepositoryShould {

  private static final URI OSOCO_URI = URI.create("http://www.osoco.es");
  public static final String OSOCO_URL_MURMUR3_HASH = "339d3b53";

  private InMemoryShortenedUrlRepository inMemoryShortenedUrlRepository;

  @Before
  public void setUp() {
    this.inMemoryShortenedUrlRepository = new InMemoryShortenedUrlRepository();
  }

  @Test public void
  store_a_shortened_url() throws MalformedURLException {

    inMemoryShortenedUrlRepository.save(OSOCO_URL_MURMUR3_HASH, OSOCO_URI.toURL());

    assertThat(inMemoryShortenedUrlRepository.findURLById(OSOCO_URL_MURMUR3_HASH).get(), is(OSOCO_URI.toURL()));
  }

  @Test public void
  return_absent_optional_for_a_non_existing_shortened_url() throws MalformedURLException {

    assertThat(inMemoryShortenedUrlRepository.findURLById(OSOCO_URL_MURMUR3_HASH).isPresent(), is(FALSE));
  }

}
