package com.example.gopherslanguagetranslator.rest.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WordRequest {

  @Getter
  @JsonProperty("english-word")
  private String englishWord;
}
