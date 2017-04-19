package com.lapsa.validation;

import java.time.LocalDateTime;

public final class LocalDateTimeComparison implements TemporalComparison<LocalDateTime> {

    private final LocalDateTime left;
    private final LocalDateTime right;

    public LocalDateTimeComparison(LocalDateTime left, LocalDateTime right) {
	this.left = left;
	this.right = right;
    }

    @Override
    public LocalDateTime left() {
	return left;
    }

    @Override
    public LocalDateTime right() {
	return right;
    }

    @Override
    public String toString() {
	return String.format("%1$s, %2$s", left, right);
    }
}
