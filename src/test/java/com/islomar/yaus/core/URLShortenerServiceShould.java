package com.islomar.yaus.core;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.net.URI;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class URLShortenerServiceShould {

  private static final URI OSOCO_URI = URI.create("http://www.osoco.es");
  private static final URI ESAILORS_URI = URI.create("http://www.esailors.de");
  public static final String OSOCO_URL_MURMUR3_HASH = "339d3b53";

  private URLShortenerService urlShortenerService;
  @Mock private ShortenedUrlRepository shortenedUrlRepository;
  @Mock private ShortenerAlgorithm shortenerAlgorithm;

  @Before
  public void setUp() {
    urlShortenerService = new URLShortenerService(shortenedUrlRepository, shortenerAlgorithm);
  }

  @Test public void
  create_one_short_url_from_valid_url() throws MalformedURLException {

    given(shortenerAlgorithm.shorten(OSOCO_URI.toString())).willReturn(OSOCO_URL_MURMUR3_HASH);

    urlShortenerService.shorten(OSOCO_URI.toString());

    InOrder inOrder = inOrder(shortenerAlgorithm, shortenedUrlRepository);
    inOrder.verify(shortenerAlgorithm).shorten(OSOCO_URI.toString());
    inOrder.verify(shortenedUrlRepository).save(OSOCO_URL_MURMUR3_HASH, OSOCO_URI.toURL());
  }
}
