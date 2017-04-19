package com.lapsa.validation;

import java.time.LocalDate;

public final class LocalDateComparison implements TemporalComparison<LocalDate> {

    private final LocalDate left;
    private final LocalDate right;

    public LocalDateComparison(LocalDate left, LocalDate right) {
	this.left = left;
	this.right = right;
    }

    @Override
    public LocalDate left() {
	return left;
    }

    @Override
    public LocalDate right() {
	return right;
    }

    @Override
    public String toString() {
	return String.format("%1$s, %2$s", left, right);
    }
}
