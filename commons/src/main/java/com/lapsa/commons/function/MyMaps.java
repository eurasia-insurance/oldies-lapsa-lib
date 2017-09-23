package com.lapsa.commons.function;

import java.util.Map;

public final class MyMaps {

    private MyMaps() {
    }

    public static <K, V> Map.Entry<K, V> entry(K k, V v) {
	return new Map.Entry<K, V>() {

	    @Override
	    public K getKey() {
		return k;
	    }

	    @Override
	    public V getValue() {
		return v;
	    }

	    @Override
	    public V setValue(V value) {
		throw new UnsupportedOperationException();
	    }
	};
    }
}
