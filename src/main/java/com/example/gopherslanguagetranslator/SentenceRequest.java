package com.example.gopherslanguagetranslator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SentenceRequest {

  private static final long serialVersionUID = 4968441131107889048L;

  @Getter
  @JsonProperty("english-sentence")
  private String englishSentence;
}
