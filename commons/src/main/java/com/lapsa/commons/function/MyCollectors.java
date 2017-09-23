package com.lapsa.commons.function;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collector;

public final class MyCollectors {

    private MyCollectors() {
    }

    public static <T, K, U> Collector<T, ?, Map<K, U>> unmodifiableMap(Function<? super T, ? extends K> keyMapper,
	    Function<? super T, ? extends U> valueMapper) {
	return collectingAndThen(toMap(keyMapper, valueMapper), Collections::unmodifiableMap);
    }

    public static <T> Collector<T, ?, List<T>> unmodifiableList() {
	return collectingAndThen(toList(), Collections::unmodifiableList);
    }
}
