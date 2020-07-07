package com.example.gopherslanguagetranslator.rest;

import com.example.gopherslanguagetranslator.exception.BadRequestException;
import com.example.gopherslanguagetranslator.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestValidator {

  public static void validateHasText(final String text) {
    if (!StringUtils.hasText(text)) {
      throw new BadRequestException("The text is not provided");
    }
  }

  public static void validateHasNoCRLF(final String text) {
    if (!text.replaceAll("(\r\n)|(\r)|(\n)", "_").equals(text)) {
      throw new BadRequestException("The provided text contain disallowed CRLF characters");
    }
  }

  public static void validateIsAWord(final String text) {
    if (text.split(" ").length > 1) {
      throw new BadRequestException("The provided text is a sentence, not a word");
    }
  }

  public static void validateIsASentence(final String text) {
    if (!StringUtils.hasEndCharacter(text)) {
      throw new BadRequestException("The provided text is not a sentence. Any sentence should end up with one of [.?!]");
    }
  }
}
