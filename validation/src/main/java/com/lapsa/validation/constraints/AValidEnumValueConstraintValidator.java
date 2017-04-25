package com.lapsa.validation.constraints;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.validation.ValidationMode;

public abstract class AValidEnumValueConstraintValidator<A extends Annotation, T extends Enum<T>>
	implements ConstraintValidator<A, T> {

    protected abstract T[] getPermited();

    protected abstract T[] getDenied();

    protected abstract ValidationMode getMode();

    @Override
    public boolean isValid(T value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;

	ValidationMode processWithMode = null;

	switch (getMode()) {
	case AUTO:
	    if (getPermited() != null && getPermited().length > 0)
		processWithMode = ValidationMode.DENY_IF_NOT_PERMITED;
	    else if (getDenied() != null && getDenied().length > 0)
		processWithMode = ValidationMode.PERMIT_IF_NOT_DENIED;
	    break;
	default:
	    processWithMode = getMode();
	    break;
	}

	switch (processWithMode) {
	case DENY_IF_NOT_PERMITED:
	    try {
		for (T s : getPermited())
		    if (s.equals(value))
			return true;
	    } catch (NullPointerException ignored) {
	    }
	    return false; // запрещено все, что не разрешено
	case PERMIT_IF_NOT_DENIED:
	    try {
		for (T s : getDenied())
		    if (s.equals(value))
			return false;
	    } catch (NullPointerException ignored) {
	    }
	    return true; // разрешено все, что не запрещено
	default:
	    return false;
	}
    }

}