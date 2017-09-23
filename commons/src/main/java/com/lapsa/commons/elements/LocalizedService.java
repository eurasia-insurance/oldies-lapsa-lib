package com.lapsa.commons.elements;

import java.util.Locale;

public interface LocalizedService<T extends Localized> {

    default String displayName(T entity) {
	return entity.displayName();
    }

    default String displayNameShort(T entity) {
	return entity.displayNameShort();
    }

    default String displayNameFull(T entity) {
	return entity.displayNameFull();
    }

    //

    default String displayName(T entity, Locale locale) {
	return entity.displayName(locale);
    }

    default String displayNameShort(T entity, Locale locale) {
	return entity.displayNameShort(locale);
    }

    default String displayNameFull(T entity, Locale locale) {
	return entity.displayNameFull(locale);
    }
}
