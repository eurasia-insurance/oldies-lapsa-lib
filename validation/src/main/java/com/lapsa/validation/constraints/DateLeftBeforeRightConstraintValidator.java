package com.lapsa.validation.constraints;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lapsa.validation.DateLeftBeforeRight;

public class DateLeftBeforeRightConstraintValidator extends ATemporalLeftRightConstraintValidator<DateLeftBeforeRight> {

    @Override
    public void initialize(DateLeftBeforeRight constraintAnnotation) {
    }

    @Override
    protected boolean compare(LocalDateTime left, LocalDateTime right) {
	if (left == null || right == null)
	    return true;
	return left.isBefore(right);
    }

    @Override
    protected boolean compare(LocalDate left, LocalDate right) {
	if (left == null || right == null)
	    return true;
	return left.isBefore(right);
    }
}