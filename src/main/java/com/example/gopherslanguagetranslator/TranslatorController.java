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
public class TranslatorController {

  private final TranslationRepository repository;

  @PostMapping("/word")
  public WordResponse translateWord(@RequestBody final WordRequest request) {
    final String englishWord = request.getEnglishWord();
    InputValidator.validateHasText(englishWord);
    InputValidator.validateHasNoCRLF(englishWord);
    InputValidator.validateIsAWord(englishWord);
    final String trimmedEnglishWord = englishWord.trim();
    final String gopherWord = TranslatorController.processWord(trimmedEnglishWord);
    final Translation translation = new Translation();
    translation.setEnglish(trimmedEnglishWord);
    translation.setGopher(gopherWord);
    this.repository.save(translation);
    return new WordResponse(gopherWord);
  }

  @PostMapping("/sentence")
  public SentenceResponse translateSentence(@RequestBody final SentenceRequest request) {
    final String englishSentence = request.getEnglishSentence();
    InputValidator.validateHasText(englishSentence);
    InputValidator.validateHasNoCRLF(englishSentence);
    InputValidator.validateIsASentence(englishSentence);
    final String trimmedEnglishSentence = englishSentence.trim();
    final String gopherSentence = TranslatorController.processSentence(trimmedEnglishSentence);
    final Translation translation = new Translation();
    translation.setEnglish(trimmedEnglishSentence);
    translation.setGopher(gopherSentence);
    this.repository.save(translation);
    return new SentenceResponse(gopherSentence);
  }

  @GetMapping("/history")
  public HistoryResponse getHistory() {
    final List<Translation> translations = this.repository.findAllByOrderByEnglishAsc();
    return new HistoryResponse(TranslatorController.convertToMap(translations));
  }

  private static LinkedHashMap<String, String> convertToMap(final List<Translation> translations) {
    return translations.stream().collect(Collectors.toMap(Translation::getEnglish, Translation::getGopher, (e1, e2) -> e2, LinkedHashMap::new));
  }

  private static String processWord(final String word) {
    return word + "_gopher";
  }

  private static String processSentence(final String sentence) {
    return sentence + "_gopher";
  }
}
