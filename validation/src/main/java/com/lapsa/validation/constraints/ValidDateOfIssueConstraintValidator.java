package com.lapsa.validation.constraints;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.lapsa.validation.ValidDateOfIssue;

public class ValidDateOfIssueConstraintValidator extends ATemporalConstraintValidator<ValidDateOfIssue> {

    @Override
    public void initialize(ValidDateOfIssue a) {
    }

    @Override
    protected boolean validate(LocalDateTime value) {
	throw unsupportedType(value.getClass());
    }

    @Override
    protected boolean validate(LocalDate value) {
	return true;
    }

    @Override
    protected boolean validate(LocalTime value) {
	throw unsupportedType(value.getClass());
    }

}