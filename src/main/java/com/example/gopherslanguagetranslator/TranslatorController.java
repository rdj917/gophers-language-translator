package com.example.gopherslanguagetranslator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TranslatorController {

  @PostMapping("/word")
  public WordResponse translateWord(@RequestBody final WordRequest request) {
    return new WordResponse(request.getEnglishWord() + "hardcode");
  }
}
