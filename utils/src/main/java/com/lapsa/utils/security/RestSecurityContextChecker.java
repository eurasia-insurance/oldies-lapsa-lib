package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;

import javax.ws.rs.core.SecurityContext;

class RestSecurityContextChecker implements SecuritySourceChecker {

    private final SecurityContext securityContext;
    private final Locale locale;

    RestSecurityContextChecker(final SecurityContext securityContext, final Locale locale) {
	this.securityContext = securityContext;
	this.locale = locale;
    }

    @Override
    public boolean isUserInRole(final SecurityRole securityRole) {
	try {
	    return securityContext.isUserInRole(securityRole.roleName());
	} catch (IllegalStateException | NullPointerException e) {
	    return false;
	}
    }

    @Override
    public Principal getUserPrincipal() {
	try {
	    return securityContext.getUserPrincipal();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public String getRemoteUser() {
	try {
	    return securityContext.getUserPrincipal().getName();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public Locale getLocale() {
	return locale;
    }
}