package com.islomar.yaus.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.net.URI;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UrlShortenerApplication.class)
@WebAppConfiguration
public class UrlShortenerRestControllerShould {

  private static final URI OSOCO_URI = URI.create("http://www.osoco.es");

  private MockMvc mockMvc;

  @Autowired
  private WebApplicationContext webApplicationContext;

  @Before
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(webApplicationContext).build();
  }

  @Test
  public void
  be_up_and_running() throws Exception {

    mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(content().string("URL Shortener up and running!"));
  }


  @Test
  public void
  create_a_short_url_from_a_post_request() throws Exception {

    mockMvc.perform(post("/")
        .contentType(MediaType.TEXT_PLAIN)
        .content(OSOCO_URI.toString())
    )
        .andExpect(status().isCreated())
        .andExpect(content().string("URL to be shortened: " + OSOCO_URI.toString()));
  }

  @Test
  public void
  recover_full_url_from_short_url() throws Exception {

    mockMvc.perform(get("/000000"))
        .andExpect(status().isOk())
        .andExpect(content().string(OSOCO_URI.toString()));
  }
}
