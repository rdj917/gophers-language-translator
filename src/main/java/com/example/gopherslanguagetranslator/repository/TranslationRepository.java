package com.example.gopherslanguagetranslator.repository;

import com.example.gopherslanguagetranslator.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TranslationRepository extends JpaRepository<Translation, Long> {

  List<Translation> findAllByOrderByEnglishAsc();
}
