package com.lapsa.utils;

import java.util.Locale;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lapsa.localization.LocalizationLanguage;

public final class RESTUtils {
    public static final String DEFAULT_ENCODING = "UTF-8";

    public static final CacheControl CACHE_CONTROL_NO_CACHE;
    static {
	CACHE_CONTROL_NO_CACHE = new CacheControl();
	CACHE_CONTROL_NO_CACHE.setMustRevalidate(true);
	CACHE_CONTROL_NO_CACHE.setNoCache(true);
	CACHE_CONTROL_NO_CACHE.setNoStore(true);
	CACHE_CONTROL_NO_CACHE.setProxyRevalidate(true);
    }

    //

    public static final Response responseServerError(Object entity) {
	return response(Status.INTERNAL_SERVER_ERROR, entity);
    }

    public static final Response responseServerError(Object entity, LocalizationLanguage language) {
	return response(Status.INTERNAL_SERVER_ERROR, language, entity);
    }

    public static final Response responseServerError(Object entity, Locale locale) {
	return response(Status.INTERNAL_SERVER_ERROR, locale, entity);
    }

    //

    public static final Response responseNotFound(Object entity) {
	return response(Status.NOT_FOUND, entity);
    }

    public static final Response responseNotFound(Object entity, LocalizationLanguage language) {
	return response(Status.NOT_FOUND, language, entity);
    }

    public static final Response responseNotFound(Object entity, Locale locale) {
	return response(Status.NOT_FOUND, locale, entity);
    }

    //

    public static final Response responseBadRequest(Object entity) {
	return response(Status.BAD_REQUEST, entity);
    }

    public static final Response responseBadRequest(Object entity, LocalizationLanguage language) {
	return response(Status.BAD_REQUEST, language, entity);
    }

    public static final Response responseBadRequest(Object entity, Locale locale) {
	return response(Status.BAD_REQUEST, locale, entity);
    }

    //

    public static final Response responseOk(Object entity) {
	return response(Status.OK, entity);
    }

    public static final Response responseOk(Object entity, LocalizationLanguage language) {
	return response(Status.OK, language, entity);
    }

    public static final Response responseOk(Object entity, Locale locale) {
	return response(Status.OK, locale, entity);
    }

    //

    public static final Response response(Status status, Object entity) {
	return response(status, Locale.getDefault(), entity);
    }

    public static final Response response(Status status, LocalizationLanguage language, Object entity) {
	return response(status, language.getLocale(), entity);
    }

    public static final Response response(Status status, Locale locale, Object entity) {
	return Response
		.status(status)
		.cacheControl(RESTUtils.CACHE_CONTROL_NO_CACHE)
		.entity(entity)
		.language(locale)
		.encoding(DEFAULT_ENCODING)
		.build();
    }

}
