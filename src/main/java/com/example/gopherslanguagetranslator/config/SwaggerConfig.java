package com.example.gopherslanguagetranslator.config;

import com.example.gopherslanguagetranslator.rest.TranslationController;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
@PropertySource("classpath:version.properties")
public class SwaggerConfig {

  public static final Set<String> PRODUCES_AND_CONSUMES =
      new HashSet<>(Collections.singletonList(MediaType.APPLICATION_JSON_VALUE));

  private final InfoSettings infoSettings;

  @Value("${application.version}")
  private String applicationVersion;

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(this.getApiInfo())
        .tags(new Tag(TranslationController.TRANSLATION_CONTROLLER_TAG, TranslationController.TRANSLATION_CONTROLLER_DESCRIPTION))
        .produces(SwaggerConfig.PRODUCES_AND_CONSUMES)
        .consumes(SwaggerConfig.PRODUCES_AND_CONSUMES)
        .select()
        .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
        .paths(PathSelectors.any())
        .build();
  }

  private ApiInfo getApiInfo() {
    return new ApiInfo(this.infoSettings.getTitle(),
        this.infoSettings.getDescription(),
        this.applicationVersion,
        null,
        this.getContact(),
        this.infoSettings.getLicense(),
        this.infoSettings.getLicenseUrl(),
        Collections.emptyList());
  }

  private Contact getContact() {
    return new Contact(this.infoSettings.getContact().getName(),
        this.infoSettings.getContact().getUrl(),
        this.infoSettings.getContact().getEmail());
  }
}
