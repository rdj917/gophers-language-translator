package com.example.gopherslanguagetranslator.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(value = "info", ignoreUnknownFields = true)
public class InfoSettings {

  private String title;

  private String description;

  private String license;

  private String licenseUrl;

  private Contact contact;

  @Getter
  @Setter
  public static class Contact {

    private String name;

    private String url;

    private String email;
  }
}
