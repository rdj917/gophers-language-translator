package com.example.gopherslanguagetranslator.rest.contract;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor
public class HistoryResponse {

  @Getter
  private final Map<String, String> history;
}
