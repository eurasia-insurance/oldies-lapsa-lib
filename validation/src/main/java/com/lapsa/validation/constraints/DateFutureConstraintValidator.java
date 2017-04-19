package com.lapsa.validation.constraints;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.lapsa.validation.DateFuture;

public class DateFutureConstraintValidator extends ATemporalConstraintValidator<DateFuture> {

    @Override
    public void initialize(DateFuture constraintAnnotation) {
    }

    @Override
    protected boolean validate(LocalDateTime value) {
	return value.isAfter(LocalDateTime.now());
    }

    @Override
    protected boolean validate(LocalDate value) {
	return value.isAfter(LocalDate.now());
    }

    @Override
    protected boolean validate(LocalTime value) {
	return value.isAfter(LocalTime.now());
    }
}
