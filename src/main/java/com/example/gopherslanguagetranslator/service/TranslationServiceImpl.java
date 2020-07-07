package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.model.Translation;
import com.example.gopherslanguagetranslator.rest.InputValidator;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class TranslationServiceImpl implements TranslationService {

  public static final String DELIMITER = " ";

  @Override
  public Translation translate(final String text) {

    final Function<String, String> function =
        body -> Arrays.stream(body.split(TranslationServiceImpl.DELIMITER))
                      .flatMap(word -> Arrays.stream(TranslationServiceImpl.removeApostrophes(word).split(TranslationServiceImpl.DELIMITER)))
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

  private static String removeApostrophes(final String word) {
    // don’t use shortened versions of won't (ex. won’t => will not)
    final Pattern wontPattern = Pattern.compile("[won't ]+");
    final Matcher wontMatcher = wontPattern.matcher(word);
    if (wontMatcher.matches()) {
      return wontMatcher.replaceAll("will not");
    }

    // don’t use shortened versions of words with apostrophes (ex. don’t => do not, shouldn’t => should not)
    final Pattern ntPattern = Pattern.compile("[n't ]+");
    final Matcher ntMatcher = ntPattern.matcher(word);
    return ntMatcher.replaceAll(" not");
  }
}
