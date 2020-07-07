package com.example.gopherslanguagetranslator.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

  public static boolean hasEndCharacter(final String text) {
    return text.endsWith(".")
           || text.endsWith("?")
           || text.endsWith("!");
  }

  public static boolean hasText(final String text) {
    return (text != null && !text.trim().isEmpty());
  }
}
