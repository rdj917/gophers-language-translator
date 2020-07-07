package com.example.gopherslanguagetranslator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InputValidator {

  public static void validateHasText(@Nullable final String text) {
    if (!StringUtils.hasText(text)) {
      throw new BadRequestException("The request has no text");
    }
  }

  public static void validateHasNoCRLF(@NonNull final String text) {
    if (!text.replaceAll("(\r\n)|(\r)|(\n)", "_").equals(text)) {
      throw new BadRequestException("The provided text contain disallowed CRLF characters");
    }
  }

  public static void validateIsAWord(@NonNull final String text) {
    if (text.split(" ").length > 1) {
      throw new BadRequestException("The provided text is a sentence not a word");
    }
  }

  public static void validateIsASentence(@NonNull final String text) {
    if (!InputValidator.hasEndCharacter(text)) {
      throw new BadRequestException("The provided text is not a sentence. Any sentence should end with one of [./?/!]");
    }
  }

  public static boolean hasEndCharacter(@NonNull final String text) {
    return text.endsWith(".")
           || text.endsWith("?")
           || text.endsWith("!");
  }
}
