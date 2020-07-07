package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.utils.StringUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GopherLanguageInterpreter {

  public static String translate(final String input) {
    String word = input;
    final StringBuilder prefixSymbols = new StringBuilder();
    final StringBuilder suffixSymbols = new StringBuilder();

    // remove symbols from start of word
    while (StringUtils.hasNonAlphabetSymbolAtTheStart(word)) {
      prefixSymbols.append(word.charAt(0));
      word = word.substring(1);
    }

    // remove symbols from end of word
    while (StringUtils.hasNonAlphabetSymbolAtTheEnd(word)) {
      suffixSymbols.append(word.charAt(StringUtils.getSuffixBeginIndex(word)));
      word = word.substring(0, StringUtils.getSuffixBeginIndex(word));
    }

    // gather back all the symbols
    final String gopherWord = GopherLanguageInterpreter.applyRules(word);
    prefixSymbols.append(gopherWord);
    prefixSymbols.append(suffixSymbols.toString());

    return prefixSymbols.toString();
  }

  private static String applyRules(final String word) {
    if (word.isEmpty()) {
      return word;
    }

    final boolean isTitle = Character.isUpperCase(word.charAt(0));

    // If a word starts with a vowel letter, add prefix “g” to the word (ex. apple => gapple)
    if (StringUtils.isVowel(word.charAt(0))) {
      final String prefix = isTitle ? "G" : "g";
      return prefix + word.toLowerCase();
    }

    // If a word starts with the consonant letters “xr”, add the prefix “ge” to the begging of the word (ex. xray => gexray)
    if (word.toLowerCase().startsWith("xr")) {
      final String prefix = isTitle ? "Ge" : "ge";
      return prefix + word.toLowerCase();
    }

    // If a word starts with a consonant sound followed by "qu", move it to the end of the word, and then add "ogo" suffix to the word
    // (ex. square => aresquogo)
    if (!StringUtils.isVowel(word.charAt(0))
        && word.length() > 2
        && word.substring(1, 3).equals("qu")) {
      return GopherLanguageInterpreter.applyOgoSuffix(word, isTitle, 3);
    }

    // If a word starts with a consonant sound, move it to the end of the word and then add “ogo” suffix to the word.
    // Consonant sounds can be made up of multiple consonants, a.k.a. a consonant cluster (ex. chair => airchogo)
    final int consonantClusterLength = StringUtils.getConsonantClusterLength(word);
    return consonantClusterLength > 0
           ? GopherLanguageInterpreter.applyOgoSuffix(word, isTitle, consonantClusterLength)
           : word;
  }

  private static String applyOgoSuffix(String translatedWord, final boolean isTitle, final int lengthOfSwitch) {
    final String firstPartRaw = translatedWord.substring(lengthOfSwitch).toLowerCase();
    final String secondPart = translatedWord.substring(0, lengthOfSwitch).toLowerCase();
    final String firstPart = isTitle
                             ? firstPartRaw.substring(0, 1).toUpperCase() + firstPartRaw.substring(1)
                             : firstPartRaw;
    translatedWord = firstPart + secondPart + "ogo";
    return translatedWord;
  }
}
