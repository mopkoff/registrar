package com.mopkoff.registrar.annotation;

import static java.lang.annotation.ElementType.FIELD;

import com.mopkoff.registrar.validation.PhoneNumberValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { PhoneNumberValidator.class})
public @interface PhoneNumberValid {

  String message() default "{validation.PhoneNumberValid.message}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
