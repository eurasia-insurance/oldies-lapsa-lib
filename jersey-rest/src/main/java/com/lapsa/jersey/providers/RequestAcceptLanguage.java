package com.lapsa.jersey.providers;

import java.util.Locale;

public class RequestAcceptLanguage {

    private static final ThreadLocal<Locale> REQUEST_LOCALE = new ThreadLocal<Locale>();

    public static Locale getRequestLocale() {
	return REQUEST_LOCALE.get() == null ? Locale.getDefault() : REQUEST_LOCALE.get();
    }

    public static void setRequestLocale(Locale requestLocale) {
	REQUEST_LOCALE.set(requestLocale);
    }

    public static void unsetRequestLocale() {
	REQUEST_LOCALE.remove();
    }

}
