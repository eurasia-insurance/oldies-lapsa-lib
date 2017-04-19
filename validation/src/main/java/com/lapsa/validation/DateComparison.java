package com.lapsa.validation;

import java.util.Date;

public final class DateComparison implements TemporalComparison<Date> {

    private final Date left;
    private final Date right;

    public DateComparison(Date left, Date right) {
	this.left = left;
	this.right = right;
    }

    @Override
    public Date left() {
	return left;
    }

    @Override
    public Date right() {
	return right;
    }

    @Override
    public String toString() {
	return String.format("%1$s, %2$s", left, right);
    }
}
