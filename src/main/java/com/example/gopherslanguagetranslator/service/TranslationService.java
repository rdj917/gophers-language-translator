package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.model.Translation;
import org.springframework.lang.NonNull;

public interface TranslationService {

  @NonNull
  Translation translate(@NonNull final String text);
}
