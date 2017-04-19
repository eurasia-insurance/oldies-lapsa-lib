package com.lapsa.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lapsa.validation.constraints.ValidDateOfBirthConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidDateOfBirthConstraintValidator.class)
public @interface ValidDateOfBirth {
    String message() default "{com.lapsa.validation.ValidDateOfBirth.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
