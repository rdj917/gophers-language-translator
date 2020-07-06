package com.example.gopherslanguagetranslator;

import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
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
    final String englishWord = StringUtils.trimWhitespace(request.getEnglishWord());
    final Translation translation = new Translation();
    translation.setEnglish(englishWord);
    final String gopherWord = englishWord + "_gopher";
    translation.setGopher(gopherWord);
    this.repository.save(translation);
    return new WordResponse(gopherWord);
  }

  @PostMapping("/sentence")
  public SentenceResponse translateSentence(@RequestBody final SentenceRequest request) {
    final String englishSentence = StringUtils.trimWhitespace(request.getEnglishSentence());
    final Translation translation = new Translation();
    translation.setEnglish(englishSentence);
    final String gopherSentence = englishSentence + "_gopher";
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
}
