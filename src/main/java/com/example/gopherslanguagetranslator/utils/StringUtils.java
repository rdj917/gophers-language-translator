package com.example.gopherslanguagetranslator.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils {

  public static int getSuffixBeginIndex(final String word) {
    return word.length() - 1;
  }

  public static int getConsonantClusterLength(final String word) {
    int i = 0;
    while (i < word.length() && !StringUtils.isVowel(word.charAt(i))) {
      i++;
    }
    return i;
  }

  public static String getNonShortenedWord(final String word) {
    final Pattern wontPattern = Pattern.compile("([won't ])+");
    final Matcher wontMatcher = wontPattern.matcher(word);

    if (wontMatcher.matches()) {
      return wontMatcher.replaceAll("will not");
    }

    //    final Pattern ntPattern = Pattern.compile("[n't ]+");
    final Pattern ntPattern = Pattern.compile("[n't ]{3}");
    final Matcher ntMatcher = ntPattern.matcher(word);

    return ntMatcher.find()
           ? ntMatcher.replaceAll(" not")
           : word;
  }

  public static boolean hasEndCharacter(final String text) {
    return text.endsWith(".")
           || text.endsWith("?")
           || text.endsWith("!");
  }

  public static boolean hasText(final String text) {
    return (text != null && !text.trim().isEmpty());
  }

  public static boolean hasNonAlphabetSymbolAtTheStart(final String word) {
    return word.length() > 1
           && !StringUtils.isAlphabet(word.substring(0, 1));
  }

  public static boolean hasNonAlphabetSymbolAtTheEnd(final String word) {
    final int index = StringUtils.getSuffixBeginIndex(word);
    return index > 0
           && word.length() > index
           && !StringUtils.isAlphabet(word.substring(index));
  }

  public static boolean isVowel(final char c) {
    return "AEIOUaeiou".indexOf(c) != -1;
  }

  public static boolean isAlphabet(final String c) {
    final Pattern alphabetPattern = Pattern.compile("[a-zA-Z]");
    final Matcher alphabetMatcher = alphabetPattern.matcher(c);
    return alphabetMatcher.matches();
  }
}
