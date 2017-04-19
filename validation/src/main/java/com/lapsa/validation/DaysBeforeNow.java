package com.lapsa.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.lapsa.validation.constraints.DaysBeforeNowConstraintValidator;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@Constraint(validatedBy = DaysBeforeNowConstraintValidator.class)
public @interface DaysBeforeNow {

    Mode mode() default Mode.MUST;

    int value();

    String message() default "{com.lapsa.validation.DaysBeforeNow.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
