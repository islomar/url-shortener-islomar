package com.islomar.yaus.core.infrastructure.services;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class Murmur3With32BitsHashGeneratorShould {

  private Murmur3With32BitsHashGenerator murmur3With32BitsHashGenerator;

  @Before
  public void setUp() {
    this.murmur3With32BitsHashGenerator = new Murmur3With32BitsHashGenerator();
  }

  @Test public void
  generate_339d3b53_for_osoco_url() {
    assertThat(murmur3With32BitsHashGenerator.generateIdFrom("http://www.osoco.es"), is("339d3b53"));
  }
}
