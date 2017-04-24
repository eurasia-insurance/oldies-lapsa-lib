package com.lapsa.jersey.providers;

import java.io.IOException;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.validation.ValidationConfig;

@Provider
public class ValidationMessagesLocalizationProvider
	implements ContextResolver<ValidationConfig>, ContainerRequestFilter {

    @Context
    private HttpHeaders headers;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
	RequestAcceptableLanguages.setLocalesList(headers.getAcceptableLanguages());
    }

    @Override
    public ValidationConfig getContext(Class<?> type) {
	final ValidationConfig config = new ValidationConfig();
	MessageInterpolator delegate = Validation.buildDefaultValidatorFactory().getMessageInterpolator();
	MessageInterpolator interpolator = new RequestAcceptableLanguagesMessageInterpolator(delegate);
	config.messageInterpolator(interpolator);
	return config;
    }

}
