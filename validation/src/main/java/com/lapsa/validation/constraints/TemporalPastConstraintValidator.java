package com.lapsa.validation.constraints;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.lapsa.validation.TemporalPast;

public class TemporalPastConstraintValidator extends ATemporalConstraintValidator<TemporalPast> {

    private boolean allowNow;

    @Override
    public void initialize(TemporalPast constraintAnnotation) {
	this.allowNow = constraintAnnotation.allowNow();
    }

    @Override
    protected boolean validate(LocalDateTime value) {
	LocalDateTime now = LocalDateTime.now();
	return value.isBefore(now) || (allowNow && value.isEqual(now));
    }

    @Override
    protected boolean validate(LocalDate value) {
	LocalDate now = LocalDate.now();
	return value.isBefore(now) || (allowNow && value.isEqual(now));
    }

    @Override
    protected boolean validate(LocalTime value) {
	LocalTime now = LocalTime.now();
	return value.isBefore(now) || (allowNow && value.equals(now));
    }

}