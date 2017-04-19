package com.lapsa.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lapsa.validation.constraints.NotNullValueConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = NotNullValueConstraintValidator.class)
public @interface NotNullValue {

    String message() default "{com.lapsa.validation.NotNullValue.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}