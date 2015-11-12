package com.islomar.yaus.core.infrastructure.repositories;

import com.islomar.yaus.core.model.ShortenedUrlRepository;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

public class PostgreSqlShortenedUrlRepository implements ShortenedUrlRepository {


  @Override
  public void save(String shortenedId, URL fullUri) {
    throw new NotImplementedException();
  }

  @Override
  public Optional<URL> findURLById(String shortenedId) {
    throw new NotImplementedException();
  }

  private Connection getConnection() throws URISyntaxException, SQLException {
    URI dbUri = new URI(System.getenv("DATABASE_URL"));

    String username = dbUri.getUserInfo().split(":")[0];
    String password = dbUri.getUserInfo().split(":")[1];
    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ":" + dbUri.getPort() + dbUri.getPath();

    return DriverManager.getConnection(dbUrl, username, password);
  }
}
