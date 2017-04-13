package com.lapsa.utils;

import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

    public static final Response responseServerError(Object entity) {
	return response(Status.INTERNAL_SERVER_ERROR, entity);
    }

    public static final Response responseNotFound(Object entity) {
	return response(Status.NOT_FOUND, entity);
    }

    public static final Response responseBadRequest(Object entity) {
	return response(Status.BAD_REQUEST, entity);
    }

    public static final Response responseOk(Object entity) {
	return response(Status.OK, entity);
    }

    public static final Response response(Status status, Object entity) {
	return Response
		.status(status)
		.cacheControl(RESTUtils.CACHE_CONTROL_NO_CACHE)
		.entity(entity)
		.encoding(DEFAULT_ENCODING)
		.build();
    }

}
