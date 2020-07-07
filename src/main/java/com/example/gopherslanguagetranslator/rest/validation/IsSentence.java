package com.example.gopherslanguagetranslator.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SentenceValidator.class)
public @interface IsSentence {

  String message() default "The provided text is not a sentence. Any sentence should end up with one of [.?!]";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
