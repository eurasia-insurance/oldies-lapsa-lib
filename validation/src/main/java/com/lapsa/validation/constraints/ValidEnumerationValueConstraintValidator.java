package com.lapsa.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.validation.ValidEnumerationValue;

public class ValidEnumerationValueConstraintValidator
	implements ConstraintValidator<ValidEnumerationValue, Enum<?>> {

    private String[] allowValues;
    private String[] denyValues;
    private int mode;

    @Override
    public void initialize(ValidEnumerationValue constraintAnnotation) {
	this.allowValues = constraintAnnotation.allowValues();
	this.denyValues = constraintAnnotation.denyValues();
	this.mode = constraintAnnotation.mode();
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;
	switch (mode) {
	case ValidEnumerationValue.DENY_ALL_NOT_ALLOWED:
	    for (String s : allowValues)
		if (s.equals(value.name()))
		    return true;
	    return false; // запрещено все, что не разрешено
	case ValidEnumerationValue.ALLOW_ALL_NOT_DENIED:
	    for (String s : denyValues)
		if (s.equals(value.name()))
		    return false;
	    return true; // разрешено все, что не запрещено
	default:
	    return false;
	}
    }

}