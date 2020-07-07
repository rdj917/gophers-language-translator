package com.example.gopherslanguagetranslator;

import org.springframework.lang.NonNull;

public interface TranslationService {

  @NonNull
  Translation translate(@NonNull final String text);
}
