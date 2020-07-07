package com.example.gopherslanguagetranslator.exception;

public class BadRequestException extends RuntimeException {

  private static final long serialVersionUID = 5090079871517987122L;

  public BadRequestException(final String message) {
    super(message);
  }
}
