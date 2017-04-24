package com.lapsa.validation.constraints;

import java.time.LocalDate;

import com.lapsa.validation.ValidDateOfBirth;

public class ValidDateOfBirthConstraintValidator extends ATemporalConstraintValidator<ValidDateOfBirth> {

    @Override
    protected boolean validate(LocalDate value) {
	return true;
    }

}