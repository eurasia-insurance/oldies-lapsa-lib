package com.lapsa.jersey.providers;

import java.util.Locale;

public class RequestAcceptLanguage {

    private static final ThreadLocal<Locale> LOCALE = new ThreadLocal<Locale>();

    public static Locale getLocale() {
	return LOCALE.get() == null ? Locale.getDefault() : LOCALE.get();
    }

    public static void setLocale(Locale locale) {
	LOCALE.set(locale);
    }

    public static void unsetLocale() {
	LOCALE.remove();
    }

}
