package com.lapsa.validation.constraints;

import java.time.LocalDate;

import com.lapsa.validation.ValidDateOfIssue;

public class ValidDateOfIssueConstraintValidator extends ATemporalConstraintValidator<ValidDateOfIssue> {

    @Override
    protected boolean validate(LocalDate value) {
	return true;
    }

}