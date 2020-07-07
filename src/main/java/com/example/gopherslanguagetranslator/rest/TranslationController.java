package com.example.gopherslanguagetranslator.rest;

import com.example.gopherslanguagetranslator.model.Translation;
import com.example.gopherslanguagetranslator.repository.TranslationRepository;
import com.example.gopherslanguagetranslator.rest.contract.HistoryResponse;
import com.example.gopherslanguagetranslator.rest.contract.SentenceRequest;
import com.example.gopherslanguagetranslator.rest.contract.SentenceResponse;
import com.example.gopherslanguagetranslator.rest.contract.WordRequest;
import com.example.gopherslanguagetranslator.rest.contract.WordResponse;
import com.example.gopherslanguagetranslator.service.TranslationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TranslationController {

  public static final String TRANSLATION_CONTROLLER_TAG = "translation-controller";

  public static final String TRANSLATION_CONTROLLER_DESCRIPTION = "Translation from English to Gopher";

  private final TranslationService translationService;

  private final TranslationRepository repository;

  @PostMapping("/word")
  @ApiOperation(value = "Translate a word in english to a word in gopher")
  public ResponseEntity<WordResponse> translateWord(@RequestBody final WordRequest request) {
    final String word = request.getEnglishWord();
    RequestValidator.validateHasText(word);
    RequestValidator.validateHasNoCRLF(word);
    RequestValidator.validateIsAWord(word);
    final Translation translation = this.translationService.translate(word);
    this.repository.save(translation);
    return ResponseEntity.status(HttpStatus.OK).body(new WordResponse(translation.getGopher()));
  }

  @PostMapping("/sentence")
  @ApiOperation(value = "Translate a sentence in english to a sentence in gopher")
  public ResponseEntity<SentenceResponse> translateSentence(@RequestBody final SentenceRequest request) {
    final String sentence = request.getEnglishSentence();
    RequestValidator.validateHasText(sentence);
    RequestValidator.validateHasNoCRLF(sentence);
    RequestValidator.validateIsASentence(sentence);
    final Translation translation = this.translationService.translate(sentence);
    this.repository.save(translation);
    return ResponseEntity.status(HttpStatus.OK).body(new SentenceResponse(translation.getGopher()));
  }

  @GetMapping("/history")
  @ApiOperation(value = "View all requested translations in ascending alphabetical order")
  public ResponseEntity<HistoryResponse> getHistory() {
    final List<Translation> translations = this.repository.findAllByOrderByEnglishAsc();
    return ResponseEntity.status(HttpStatus.OK).body(new HistoryResponse(TranslationController.convertToMap(translations)));
  }

  private static LinkedHashMap<String, String> convertToMap(final List<Translation> translations) {
    return translations.stream().collect(Collectors.toMap(Translation::getEnglish, Translation::getGopher, (e1, e2) -> e2, LinkedHashMap::new));
  }
}
