package com.lapsa.jersey.providers;

import java.util.List;
import java.util.Locale;

import javax.validation.MessageInterpolator;

import com.lapsa.localization.LocalizationLanguage;

public class RequestAcceptableLanguagesMessageInterpolator implements MessageInterpolator {

    private final MessageInterpolator delegate;

    public RequestAcceptableLanguagesMessageInterpolator(MessageInterpolator delegate) {
	this.delegate = delegate;
    }

    @Override
    public String interpolate(String message, Context context) {
	List<Locale> locales = RequestAcceptableLanguages.getLocalesList();
	LocalizationLanguage lang = LocalizationLanguage.byLocalePriorityListOrDefault(locales);
	return delegate.interpolate(message, context, lang.getLocale());
    }

    @Override
    public String interpolate(String message, Context context, Locale locale) {
	return delegate.interpolate(message, context, locale);
    }
}
