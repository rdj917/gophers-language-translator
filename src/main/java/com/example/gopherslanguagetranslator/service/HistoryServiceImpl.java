package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.model.Translation;
import com.example.gopherslanguagetranslator.repository.TranslationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

  private final TranslationRepository repository;

  @Override
  public LinkedHashMap<String, String> getHistory() {
    final List<Translation> translations = this.repository.findAllByOrderByEnglishAsc();
    return HistoryServiceImpl.convertToMap(translations);
  }

  private static LinkedHashMap<String, String> convertToMap(final List<Translation> translations) {
    return translations.stream()
                       .collect(Collectors.toMap(
                           Translation::getEnglish,
                           Translation::getGopher,
                           (e1, e2) -> e2,
                           LinkedHashMap::new));
  }
}
