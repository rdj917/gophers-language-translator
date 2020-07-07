package com.example.gopherslanguagetranslator.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

  private final Date timestamp;

  private final List<String> messages;

  private final String details;
}
