package com.example.gopherslanguagetranslator.rest.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CRLFValidator implements ConstraintValidator<DisableCRLF, Object> {

  private String messageTemplate;

  @Override
  public void initialize(final DisableCRLF annotation) {
    this.messageTemplate = annotation.message();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    final boolean isValid = ((String) value).replaceAll("(\r\n)|(\r)|(\n)", "_").equals(value);

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(this.messageTemplate).addConstraintViolation();
    }

    return isValid;
  }
}
