package com.example.gopherslanguagetranslator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InputValidator {

  static void validateHasText(@Nullable final String text) {
    if (!StringUtils.hasText(text)) {
      throw new BadRequestException("The request has no text");
    }
  }

  static void validateHasNoCRLF(@NonNull final String text) {
    if (!text.replaceAll("(\r\n)|(\r)|(\n)", "_").equals(text)) {
      throw new BadRequestException("The provided text contain disallowed CRLF characters");
    }
  }

  static void validateIsAWord(@NonNull final String text) {
    if (text.split(" ").length > 1) {
      throw new BadRequestException("The provided text is a sentence not a word");
    }
  }

  static void validateIsASentence(@NonNull final String text) {
    if (!text.endsWith(".")
        && !text.endsWith("?")
        && !text.endsWith("!")) {
      throw new BadRequestException("The provided text is not a sentence. Any sentence should end with one of [./?/!]");
    }
  }
}
