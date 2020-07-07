package com.example.gopherslanguagetranslator.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class SentenceResponse {

  @Getter
  @JsonProperty("gopher-sentence")
  private final String gopherSentence;
}
