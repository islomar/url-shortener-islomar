package com.islomar.yaus.core;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class URLShortenerServiceShould {

  private static final URI OSOCO_URI = URI.create("http://www.osoco.es");
  private static final URI ESAILORS_URI = URI.create("http://www.esailors.de");

  private URLShortenerService urlShortenerService;
  @Mock private ShortenedUrlRepository shortenedUrlRepository;

  @Before
  public void setUp() {
    urlShortenerService = new URLShortenerService(shortenedUrlRepository);
  }

  @Test public void
  create_one_short_url_from_valid_url() throws MalformedURLException {

    URI osocoShortenedURL = urlShortenerService.shorten(OSOCO_URI.toString());

    verify(shortenedUrlRepository).save("339d3b53", OSOCO_URI.toURL());
  }

  @Test public void
  create_two_short_urls() throws MalformedURLException {

    urlShortenerService.shorten(OSOCO_URI.toString());

    URI esailorsShortenedURL = urlShortenerService.shorten(ESAILORS_URI.toString());

    //TODO assert behavior
    assertThat(esailorsShortenedURL.toString(), is("http://oso.co/e7ba36c9"));
  }

}
