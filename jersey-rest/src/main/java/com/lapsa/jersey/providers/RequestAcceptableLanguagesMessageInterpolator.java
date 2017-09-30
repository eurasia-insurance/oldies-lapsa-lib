package com.lapsa.jersey.providers;

import java.util.List;
import java.util.Locale;

import javax.validation.MessageInterpolator;

public class RequestAcceptableLanguagesMessageInterpolator implements MessageInterpolator {

    private final MessageInterpolator delegate;

    public RequestAcceptableLanguagesMessageInterpolator(MessageInterpolator delegate) {
	this.delegate = delegate;
    }

    @Override
    public String interpolate(String message, Context context) {
	List<Locale> locales = RequestAcceptableLanguages.getLocalesList();
	if (locales != null)
	    for (Locale locale : locales)
		try {
		    return delegate.interpolate(message, context, locale);
		} catch (Exception e) {
		    // continue to interpolating in next listed locale
		}

	return delegate.interpolate(message, context);
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
	return delegate.interpolate(message, context, locale);
    }
}
