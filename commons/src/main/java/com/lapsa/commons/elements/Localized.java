package com.lapsa.commons.elements;

import java.util.Locale;

public interface Localized {

    enum DisplayNameVariant {
	NORMAL, FULL, SHORT
    }

    //

    default String displayName(DisplayNameVariant variant) {
	return displayName(variant, Locale.getDefault());
    }

    String displayName(DisplayNameVariant variant, Locale locale);

    //

    default String displayName() {
	return displayName(Localized.DisplayNameVariant.NORMAL, Locale.getDefault());
    }

    default String displayNameFull() {
	return displayName(Localized.DisplayNameVariant.FULL, Locale.getDefault());
    }

    default String displayNameShort() {
	return displayName(Localized.DisplayNameVariant.SHORT, Locale.getDefault());
    }

    //

    default String displayName(final Locale locale) {
	return displayName(DisplayNameVariant.NORMAL, locale);
    }

    default String displayNameFull(final Locale locale) {
	return displayName(DisplayNameVariant.FULL, locale);
    }

    default String displayNameShort(final Locale locale) {
	return displayName(DisplayNameVariant.SHORT, locale);
    }

}