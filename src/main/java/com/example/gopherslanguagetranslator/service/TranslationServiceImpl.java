package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.model.Translation;
import com.example.gopherslanguagetranslator.utils.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class TranslationServiceImpl implements TranslationService {

  public static final String DELIMITER = " ";

  @Override
  public Translation translate(final String text) {

    final Function<String, String> function =
        body -> Arrays.stream(body.split(TranslationServiceImpl.DELIMITER))
                      .flatMap(TranslationServiceImpl::manageShortenedWord)
                      .map(GopherLanguageInterpreter::translate)
                      .collect(Collectors.joining(TranslationServiceImpl.DELIMITER));

    final Translation translation = new Translation();
    translation.setEnglish(text.trim());
    translation.setGopher(TranslationServiceImpl.getGopher(text.trim(), function));

    return translation;
  }

  private static Stream<? extends String> manageShortenedWord(final String word) {
    // don’t use shortened versions of words with apostrophes (ex. won’t => will not, don’t => do not, shouldn’t => should not)
    final String nonShortened = StringUtils.getNonShortenedWord(word);

    // split to multiple words if needed
    if (nonShortened.contains(TranslationServiceImpl.DELIMITER)) {
      return Arrays.stream(nonShortened.split(TranslationServiceImpl.DELIMITER));
    }

    return Stream.of(nonShortened);
  }

  private static String getGopher(final String text, final Function<String, String> function) {
    if (StringUtils.hasEndCharacter(text)) {
      return function.apply(text.substring(0, StringUtils.getSuffixBeginIndex(text)))
                     .concat(text.substring(StringUtils.getSuffixBeginIndex(text)));
    }
    return function.apply(text);
  }
}
