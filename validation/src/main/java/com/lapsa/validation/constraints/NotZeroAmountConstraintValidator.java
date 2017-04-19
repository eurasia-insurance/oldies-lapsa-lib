package com.lapsa.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.validation.NotZeroAmount;

public class NotZeroAmountConstraintValidator
	implements ConstraintValidator<NotZeroAmount, Number> {

    public void initialize(NotZeroAmount a) {
    }

    public boolean isValid(Number value, ConstraintValidatorContext cvc) {
	if (value == null)
	    return true;
	if (value instanceof Double)
	    return value.doubleValue() > 0;
	if (value instanceof Integer)
	    return value.intValue() > 0;
	if (value instanceof Long)
	    return value.longValue() > 0;
	if (value instanceof Short)
	    return value.shortValue() > 0;
	if (value instanceof Byte)
	    return value.byteValue() > 0;
	if (value instanceof Float)
	    return value.floatValue() > 0;
	return false;
    }
}