package com.example.gopherslanguagetranslator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GopherLanguageInterpreter {

  public static String translate(final String word) {
    final boolean isTitle = Character.isUpperCase(word.charAt(0));

    // If a word starts with a vowel letter, add prefix “g” to the word (ex. apple => gapple)
    if (GopherLanguageInterpreter.isVowel(word.charAt(0))) {
      final String prefix = isTitle ? "G" : "g";
      return prefix + word;
    }

    // If a word starts with the consonant letters “xr”, add the prefix “ge” to the begging of the word (ex. exray => gexray)
    if (word.toLowerCase().startsWith("xr")) {
      final String prefix = isTitle ? "Ge" : "ge";
      return prefix + word.toLowerCase();
    }

    // If a word starts with a consonant sound followed by "qu", move it to the end of the word, and then add "ogo" suffix to the word
    // (ex. square => aresquogo)
    if (!GopherLanguageInterpreter.isVowel(word.charAt(0))
        && word.length() > 2
        && word.substring(1, 3).equals("qu")) {
      return GopherLanguageInterpreter.applyOgoSuffix(word, isTitle, 3);
    }

    // If a word starts with a consonant sound, move it to the end of the word and then add “ogo” suffix to the word.
    // Consonant sounds can be made up of multiple consonants, a.k.a. a consonant cluster (ex. chair => airchogo)
    final int consonantClusterLength = GopherLanguageInterpreter.getConsonantClusterLength(word);
    if (consonantClusterLength > 0) {
      return GopherLanguageInterpreter.applyOgoSuffix(word, isTitle, consonantClusterLength);
    }

    return word;
  }

  private static int getConsonantClusterLength(final String word) {
    int i = 0;
    while (i < word.length() && !GopherLanguageInterpreter.isVowel(word.charAt(i))) {
      i++;
    }
    return i;
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

  private static boolean isVowel(final char c) {
    return "AEIOUaeiou".indexOf(c) != -1;
  }
}
