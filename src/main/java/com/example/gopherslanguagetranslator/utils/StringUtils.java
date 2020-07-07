package com.example.gopherslanguagetranslator.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

  public static boolean hasEndCharacter(@NonNull final String text) {
    return text.endsWith(".")
           || text.endsWith("?")
           || text.endsWith("!");
  }

  public static boolean hasText(@Nullable final String text) {
    return (text != null && !text.trim().isEmpty());
  }
}
