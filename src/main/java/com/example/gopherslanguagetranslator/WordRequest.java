package com.example.gopherslanguagetranslator;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WordRequest {

  @Getter
  @JsonProperty("english-word")
  private String englishWord;
}
