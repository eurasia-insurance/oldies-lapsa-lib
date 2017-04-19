package com.lapsa.validation.constraints;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.validation.NotTooOldYearOfIssue;

public class NotTooOldYearOfIssueConstraintValidator
	implements ConstraintValidator<NotTooOldYearOfIssue, Integer> {

    private int maxAge;

    public void initialize(NotTooOldYearOfIssue a) {
	maxAge = a.maxAge();
    }

    public boolean isValid(Integer value, ConstraintValidatorContext cvc) {
	if (value == null)
	    return true;
	
	int nowY = LocalDate.now().getYear();
	int age = nowY - value;
	return age <= maxAge;
    }
}