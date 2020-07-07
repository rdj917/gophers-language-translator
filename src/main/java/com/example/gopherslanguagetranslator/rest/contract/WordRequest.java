package com.example.gopherslanguagetranslator.rest.contract;

import com.example.gopherslanguagetranslator.rest.validation.DisableCRLF;
import com.example.gopherslanguagetranslator.rest.validation.IsWord;
import com.example.gopherslanguagetranslator.rest.validation.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WordRequest {

  @Getter
  @NotEmpty
  @IsWord
  @DisableCRLF
  @JsonProperty("english-word")
  private String englishWord;
}
