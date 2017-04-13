package com.lapsa.utils;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

public final class BeanUtils {

    public static final <T> T getBean(Class<T> clazz) {
	try {
	    return getBean11(clazz);
	} catch (Throwable e) {
	    try {
		return getBean10(clazz);
	    } catch (Throwable e1) {
		return null;
	    }
	}
    }

    private static <T> T getBean11(Class<T> clazz) {
	return CDI.current().select(clazz).get();
    }

    @SuppressWarnings("unchecked")
    private static <T> T getBean10(Class<T> clazz) {
	BeanManager bm = CDI.current().getBeanManager();
	Bean<T> bean = (Bean<T>) bm.getBeans(clazz).iterator().next();
	CreationalContext<T> ctx = bm.createCreationalContext(bean);
	T ref = (T) bm.getReference(bean, clazz, ctx);
	return ref;
    }

}
