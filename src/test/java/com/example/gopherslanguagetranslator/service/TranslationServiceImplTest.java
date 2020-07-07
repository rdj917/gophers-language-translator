package com.example.gopherslanguagetranslator.service;

import com.example.gopherslanguagetranslator.model.Translation;
import com.example.gopherslanguagetranslator.repository.TranslationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
class TranslationServiceImplTest {

  @Mock
  private TranslationRepository repository;

  @InjectMocks
  private TranslationServiceImpl service;

  @Test
  void testRuleResponseConsistency() {
    final Translation result = this.service.translate("string");
    assertNotNull(result);
    assertNotNull(result.getEnglish());
    assertNotNull(result.getGopher());
    assertEquals(1, result.getGopher().split(" ").length);
  }

  @Test
  void testRule1() {
    final Translation result = this.service.translate("apple");
    assertEquals("gapple", result.getGopher());
  }

  @Test
  void testRule1PreservesTheCapitalLetter() {
    final Translation result = this.service.translate("Apple");
    assertEquals("Gapple", result.getGopher());
  }

  @Test
  void testRule2() {
    final Translation result = this.service.translate("xray");
    assertEquals("gexray", result.getGopher());
  }

  @Test
  void testRule2PreservesTheCapitalLetter() {
    final Translation result = this.service.translate("Xray");
    assertEquals("Gexray", result.getGopher());
  }

  @Test
  void testRule3() {
    final Translation result = this.service.translate("chair");
    assertEquals("airchogo", result.getGopher());
  }

  @Test
  void testRule3PreservesTheCapitalLetter() {
    final Translation result = this.service.translate("Chair");
    assertEquals("Airchogo", result.getGopher());
  }

  @Test
  void testRule3WithBiggerConsonantCluster() {
    final Translation result = this.service.translate("schedule");
    assertEquals("eduleschogo", result.getGopher());
  }

  @Test
  void testRule4() {
    final Translation result = this.service.translate("square");
    assertEquals("aresquogo", result.getGopher());
  }

  @Test
  void testRule4PreservesTheCapitalLetter() {
    final Translation result = this.service.translate("Square");
    assertEquals("Aresquogo", result.getGopher());
  }

  @Test
  void testNoShortenedWordsInGopherTranslationCase1() {
    final Translation result = this.service.translate("won't");
    assertFalse(result.getGopher().contains("'"));
    assertEquals("illwogo otnogo", result.getGopher());
  }

  @Test
  void testNoShortenedWordsInGopherTranslationCase2() {
    final Translation result = this.service.translate("don't");
    assertFalse(result.getGopher().contains("'"));
    assertEquals("odogo otnogo", result.getGopher());
  }

  @Test
  void testNoShortenedWordsInGopherTranslationCase3() {
    final Translation result = this.service.translate("wouldn't");
    assertFalse(result.getGopher().contains("'"));
    assertEquals("ouldwogo otnogo", result.getGopher());
  }

  @Test
  void testNoShortenedWordsInGopherTranslationCase4() {
    final Translation result = this.service.translate("shouldn't");
    assertFalse(result.getGopher().contains("'"));
    assertEquals("ouldshogo otnogo", result.getGopher());
  }

  @Test
  void testNoShortenedWordsInGopherTranslationCornerCase() {
    final Translation result = this.service.translate("shouldnt");
    assertEquals("ouldntshogo", result.getGopher());
  }
}