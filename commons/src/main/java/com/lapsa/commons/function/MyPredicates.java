package com.lapsa.commons.function;

import java.util.function.Predicate;

public final class MyPredicates {
    private MyPredicates() {
    }

    public static <T> Predicate<T> objectNotNull() {
	return x -> x != null;
    }

    public static <T> Predicate<T> objectEquals(final T s) {
	return x -> (x == null && s == null) || (x != null && x.equals(s));
    }
}
