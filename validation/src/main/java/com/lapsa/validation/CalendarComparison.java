package com.lapsa.validation;

import java.util.Calendar;

public final class CalendarComparison implements TemporalComparison<Calendar> {

    private final Calendar left;
    private final Calendar right;

    public CalendarComparison(Calendar left, Calendar right) {
	this.left = left;
	this.right = right;
    }

    @Override
    public Calendar left() {
	return left;
    }

    @Override
    public Calendar right() {
	return right;
    }

    @Override
    public String toString() {
	return String.format("%1$s, %2$s", left, right);
    }
}
