package com.example.gopherslanguagetranslator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

  List<Translation> findAllByOrderByEnglishAsc();
}
