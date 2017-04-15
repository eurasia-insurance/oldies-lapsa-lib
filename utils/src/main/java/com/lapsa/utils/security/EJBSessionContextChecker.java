package com.lapsa.utils.security;

import java.security.Principal;

import javax.ejb.SessionContext;

class EJBSessionContextChecker implements SecuritySourceChecker {

    private final SessionContext ejbSessionContext;

    EJBSessionContextChecker(SessionContext ejbSessionContext) {
	this.ejbSessionContext = ejbSessionContext;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	try {
	    return ejbSessionContext.isCallerInRole(securityRole.name());
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
}