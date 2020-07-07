package com.example.gopherslanguagetranslator.rest.contract;

import com.example.gopherslanguagetranslator.rest.validation.DisableCRLF;
import com.example.gopherslanguagetranslator.rest.validation.IsSentence;
import com.example.gopherslanguagetranslator.rest.validation.NotEmpty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SentenceRequest {

  @Getter
  @NotEmpty
  @IsSentence
  @DisableCRLF
  @JsonProperty("english-sentence")
  private String englishSentence;
}
