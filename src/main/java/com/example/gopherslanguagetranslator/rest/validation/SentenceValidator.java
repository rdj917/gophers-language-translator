package com.example.gopherslanguagetranslator.rest.validation;

import com.example.gopherslanguagetranslator.utils.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SentenceValidator implements ConstraintValidator<IsSentence, Object> {

  private String messageTemplate;

  @Override
  public void initialize(final IsSentence annotation) {
    this.messageTemplate = annotation.message();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    final boolean isValid = StringUtils.hasEndCharacter((String) value);

    if (!isValid) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(this.messageTemplate).addConstraintViolation();
    }

    return isValid;
  }
}
