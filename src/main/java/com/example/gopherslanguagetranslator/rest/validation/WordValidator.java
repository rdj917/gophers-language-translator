package com.example.gopherslanguagetranslator.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class WordValidator implements ConstraintValidator<IsWord, Object> {

  private String messageTemplate;

  @Override
  public void initialize(final IsWord annotation) {
    this.messageTemplate = annotation.message();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    final boolean isValid = ((String) value).split(" ").length == 1;

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(this.messageTemplate).addConstraintViolation();
    }

    return isValid;
  }
}
