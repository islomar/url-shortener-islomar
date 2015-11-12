package com.islomar.yaus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class UrlShortenerApplication {

  private final static Logger LOG = LoggerFactory.getLogger(UrlShortenerApplication.class);

  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(UrlShortenerApplication.class, args);

    LOG.debug("Let's inspect the beans provided by Spring Boot:");

    String[] beanNames = ctx.getBeanDefinitionNames();
    Arrays.sort(beanNames);
    for (String beanName : beanNames) {
      LOG.debug(beanName);
    }
  }
}
