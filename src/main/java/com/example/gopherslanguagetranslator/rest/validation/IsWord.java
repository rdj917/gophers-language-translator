package com.example.gopherslanguagetranslator.rest.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = WordValidator.class)
public @interface IsWord {

  String message() default "The provided text contains multiple words";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
