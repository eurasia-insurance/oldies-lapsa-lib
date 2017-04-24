package com.lapsa.jersey.providers;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class RequestAcceptableLanguages {

    private static final ThreadLocal<List<Locale>> LOCALES_LIST = new ThreadLocal<List<Locale>>();

    public static List<Locale> getLocalesList() {
	return LOCALES_LIST.get() == null ? Collections.emptyList() : LOCALES_LIST.get();
    }

    public static void setLocalesList(List<Locale> localesList) {
	LOCALES_LIST.set(localesList);
    }
}
