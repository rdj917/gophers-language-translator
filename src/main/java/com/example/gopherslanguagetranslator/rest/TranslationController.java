package com.example.gopherslanguagetranslator.rest;

import com.example.gopherslanguagetranslator.model.Translation;
import com.example.gopherslanguagetranslator.rest.contract.HistoryResponse;
import com.example.gopherslanguagetranslator.rest.contract.SentenceRequest;
import com.example.gopherslanguagetranslator.rest.contract.SentenceResponse;
import com.example.gopherslanguagetranslator.rest.contract.WordRequest;
import com.example.gopherslanguagetranslator.rest.contract.WordResponse;
import com.example.gopherslanguagetranslator.service.HistoryService;
import com.example.gopherslanguagetranslator.service.TranslationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.LinkedHashMap;

@RestController
@RequiredArgsConstructor
public class TranslationController {

  public static final String TRANSLATION_CONTROLLER_TAG = "translation-controller";

  public static final String TRANSLATION_CONTROLLER_DESCRIPTION = "Translation from English to Gopher";

  private final TranslationService translationService;

  private final HistoryService historyService;

  @PostMapping("/word")
  @ApiOperation(value = "Translate a word in english to a word in gopher")
  public ResponseEntity<WordResponse> translateWord(@RequestBody @Valid final WordRequest request) {
    final Translation translation = this.translationService.translate(request.getEnglishWord());
    return ResponseEntity.status(HttpStatus.OK).body(new WordResponse(translation.getGopher()));
  }

  @PostMapping("/sentence")
  @ApiOperation(value = "Translate a sentence in english to a sentence in gopher")
  public ResponseEntity<SentenceResponse> translateSentence(@RequestBody @Valid final SentenceRequest request) {
    final Translation translation = this.translationService.translate(request.getEnglishSentence());
    return ResponseEntity.status(HttpStatus.OK).body(new SentenceResponse(translation.getGopher()));
  }

  @GetMapping("/history")
  @ApiOperation(value = "View all requested translations in ascending alphabetical order")
  public ResponseEntity<HistoryResponse> getHistory() {
    final LinkedHashMap<String, String> history = this.historyService.getHistory();
    return ResponseEntity.status(HttpStatus.OK).body(new HistoryResponse(history));
  }
}
