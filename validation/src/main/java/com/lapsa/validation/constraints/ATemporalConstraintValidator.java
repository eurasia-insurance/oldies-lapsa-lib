package com.lapsa.validation.constraints;

import static com.lapsa.utils.TemporalUtils.*;

import java.lang.annotation.Annotation;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

public abstract class ATemporalConstraintValidator<A extends Annotation> implements ConstraintValidator<A, Object> {

    @Override
    public void initialize(A a) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;
	if (value instanceof Date)
	    return validate(toLocalDateTime((Date) value));
	if (value instanceof Calendar)
	    return validate(toLocalDateTime((Calendar) value));
	if (value instanceof LocalTime)
	    return validate((LocalTime) value);
	if (value instanceof LocalDate)
	    return validate((LocalDate) value);
	if (value instanceof LocalDateTime)
	    return validate((LocalDateTime) value);
	throw unsupportedType(value.getClass());
    }

    private static ValidationException unsupportedType(Class<?> clazz) {
	return new ValidationException(String.format("%1$s isn't supported for this constraint", clazz));
    }

    protected boolean validate(LocalDateTime value) {
	throw unsupportedType(value.getClass());
    }

    protected boolean validate(LocalDate value) {
	throw unsupportedType(value.getClass());
    }

    protected boolean validate(LocalTime value) {
	throw unsupportedType(value.getClass());
    }

}
