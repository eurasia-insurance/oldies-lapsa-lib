package com.lapsa.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.validation.NotNullValue;

public class NotNullValueConstraintValidator implements ConstraintValidator<NotNullValue, Object> {

    @Override
    public void initialize(NotNullValue constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	return value != null;
    }

}