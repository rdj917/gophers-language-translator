package com.example.gopherslanguagetranslator;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

  private final Date timestamp;

  private final String message;

  private final String details;
}
