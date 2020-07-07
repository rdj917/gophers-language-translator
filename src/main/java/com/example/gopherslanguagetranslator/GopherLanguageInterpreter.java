package com.example.gopherslanguagetranslator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GopherLanguageInterpreter {

  public static String translate(final String word) {
    final boolean isTitle = Character.isUpperCase(word.charAt(0));

    if (GopherLanguageInterpreter.isVowel(word.charAt(0))) {
      final String prefix = isTitle ? "G" : "g";
      return prefix + word;
    }

    if (word.toLowerCase().startsWith("xr")) {
      final String prefix = isTitle ? "Ge" : "ge";
      return prefix + word.toLowerCase();
    }

    if (!GopherLanguageInterpreter.isVowel(word.charAt(0))
        && word.length() > 2
        && word.substring(1, 3).equals("qu")) {
      return GopherLanguageInterpreter.applyOgoSuffix(word, isTitle, 3);
    }

    if (GopherLanguageInterpreter.startsWithConsonant(word)) {
      return GopherLanguageInterpreter.applyOgoSuffix(word, isTitle, 2);
    }

    final Pattern wontPattern = Pattern.compile("( won't )");
    final Matcher wontMatcher = wontPattern.matcher(word);
    final String semiCleaned = wontMatcher.replaceAll("would not ");

    final Pattern ntPattern = Pattern.compile("(n't )");
    final Matcher ntMatcher = ntPattern.matcher(semiCleaned);
    return ntMatcher.replaceAll(" not ");
  }

  private static String applyOgoSuffix(String translatedWord, final boolean isCapitalLetter, final int lengthOfSwitch) {
    final String firstPartRaw = translatedWord.substring(lengthOfSwitch).toLowerCase();
    final String secondPart = translatedWord.substring(0, lengthOfSwitch).toLowerCase();
    final String firstPart = isCapitalLetter
                             ? firstPartRaw.substring(0, 1).toUpperCase() + firstPartRaw.substring(1)
                             : firstPartRaw;
    translatedWord = firstPart + secondPart + "ogo";
    return translatedWord;
  }

  private static boolean startsWithConsonant(final String text) {
    return !GopherLanguageInterpreter.isVowel(text.charAt(0)) && !GopherLanguageInterpreter.isVowel(text.charAt(1));
  }

  private static boolean isVowel(final char c) {
    return "AEIOUaeiou".indexOf(c) != -1;
  }
}
