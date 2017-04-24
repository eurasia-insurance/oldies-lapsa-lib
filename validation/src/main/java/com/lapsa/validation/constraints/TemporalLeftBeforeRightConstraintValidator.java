package com.lapsa.validation.constraints;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.lapsa.validation.TemporalLeftBeforeRight;

public class TemporalLeftBeforeRightConstraintValidator extends ATemporalLeftRightConstraintValidator<TemporalLeftBeforeRight> {

    @Override
    public void initialize(TemporalLeftBeforeRight constraintAnnotation) {
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