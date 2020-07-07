package com.example.gopherslanguagetranslator;

import lombok.RequiredArgsConstructor;
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

  private final TranslationService translationService;

  private final TranslationRepository repository;

  @PostMapping("/word")
  public WordResponse translateWord(@RequestBody final WordRequest request) {
    final String word = request.getEnglishWord();
    InputValidator.validateHasText(word);
    InputValidator.validateHasNoCRLF(word);
    InputValidator.validateIsAWord(word);
    final Translation translation = this.translationService.translate(word);
    this.repository.save(translation);
    return new WordResponse(translation.getGopher());
  }

  @PostMapping("/sentence")
  public SentenceResponse translateSentence(@RequestBody final SentenceRequest request) {
    final String sentence = request.getEnglishSentence();
    InputValidator.validateHasText(sentence);
    InputValidator.validateHasNoCRLF(sentence);
    InputValidator.validateIsASentence(sentence);
    final Translation translation = this.translationService.translate(sentence);
    this.repository.save(translation);
    return new SentenceResponse(translation.getGopher());
  }

  @GetMapping("/history")
  public HistoryResponse getHistory() {
    final List<Translation> translations = this.repository.findAllByOrderByEnglishAsc();
    return new HistoryResponse(TranslationController.convertToMap(translations));
  }

  private static LinkedHashMap<String, String> convertToMap(final List<Translation> translations) {
    return translations.stream().collect(Collectors.toMap(Translation::getEnglish, Translation::getGopher, (e1, e2) -> e2, LinkedHashMap::new));
  }
}
