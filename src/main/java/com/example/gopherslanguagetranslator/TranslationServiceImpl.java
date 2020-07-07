package com.example.gopherslanguagetranslator;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TranslationServiceImpl implements TranslationService {

  public static final String DELIMITER = " ";

  @Override
  public Translation translate(final String text) {

    final Function<String, String> function = body -> Arrays.stream(body.split(TranslationServiceImpl.DELIMITER))
                                                            .map(GopherLanguageInterpreter::translate)
                                                            .collect(Collectors.joining(TranslationServiceImpl.DELIMITER));

    final Translation translation = new Translation();
    translation.setEnglish(text.trim());
    translation.setGopher(TranslationServiceImpl.getGopher(text.trim(), function));

    return translation;
  }

  private static String getGopher(final String text, final Function<String, String> function) {
    if (InputValidator.hasEndCharacter(text)) {
      return function.apply(text.substring(0, text.length() - 1))
                     .concat(text.substring(text.length() - 1));
    }
    return function.apply(text);
  }
}
