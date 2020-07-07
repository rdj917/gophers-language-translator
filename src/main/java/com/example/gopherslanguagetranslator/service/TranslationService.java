package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.model.Translation;

public interface TranslationService {

  Translation translate(final String text);
}
