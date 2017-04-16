package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

class HttpServletRequestChecker implements SecuritySourceChecker {

    private final HttpServletRequest httpServletRequest;

    HttpServletRequestChecker(HttpServletRequest httpServletRequest) {
	this.httpServletRequest = httpServletRequest;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	try {
	    return httpServletRequest.isUserInRole(securityRole.name());
	} catch (IllegalStateException | NullPointerException e) {
	    return false;
	}
    }

    @Override
    public Principal getUserPrincipal() {
	try {
	    return httpServletRequest.getUserPrincipal();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public String getRemoteUser() {
	try {
	    return httpServletRequest.getRemoteUser();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public Locale getLocale() {
	return httpServletRequest.getLocale();
    }
}