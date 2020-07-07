package com.example.gopherslanguagetranslator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public class HistoryResponse {

  @Getter
  private final Map<String, String> history;
}
