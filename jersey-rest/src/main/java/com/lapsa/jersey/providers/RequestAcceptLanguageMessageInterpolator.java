package com.lapsa.jersey.providers;

import java.util.Locale;

import javax.validation.MessageInterpolator;

public class RequestAcceptLanguageMessageInterpolator implements MessageInterpolator {

    private final MessageInterpolator delegate;

    public RequestAcceptLanguageMessageInterpolator(MessageInterpolator delegate) {
	this.delegate = delegate;
    }

    @Override
    public String interpolate(String message, Context context) {
	return delegate.interpolate(message, context, RequestAcceptLanguage.getLocale());
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
	return delegate.interpolate(message, context, locale);
    }
}
