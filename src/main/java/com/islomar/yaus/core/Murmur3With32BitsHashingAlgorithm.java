package com.islomar.yaus.core;

import com.google.common.hash.Hashing;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

@Component
public class Murmur3With32BitsHashingAlgorithm implements ShortenerAlgorithm {

  @Override
  public String shorten(String textToBeShortened) {
    return Hashing.murmur3_32().hashString(textToBeShortened.toString(), StandardCharsets.UTF_8).toString();
  }
}
