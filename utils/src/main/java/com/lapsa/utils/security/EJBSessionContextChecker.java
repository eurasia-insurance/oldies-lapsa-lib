package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;

import javax.ejb.SessionContext;

class EJBSessionContextChecker implements SecuritySourceChecker {

    private final SessionContext ejbSessionContext;
    private final Locale locale;

    EJBSessionContextChecker(SessionContext ejbSessionContext, Locale locale) {
	this.ejbSessionContext = ejbSessionContext;
	this.locale = locale;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	try {
	    return ejbSessionContext.isCallerInRole(securityRole.roleName());
	} catch (IllegalStateException | NullPointerException e) {
	    return false;
	}
    }

    @Override
    public Principal getUserPrincipal() {
	try {
	    return ejbSessionContext.getCallerPrincipal();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public String getRemoteUser() {
	try {
	    return ejbSessionContext.getCallerPrincipal().getName();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public Locale getLocale() {
	return locale;
    }
}