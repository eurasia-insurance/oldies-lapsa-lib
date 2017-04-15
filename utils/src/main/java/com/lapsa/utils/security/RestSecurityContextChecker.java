package com.lapsa.utils.security;

import java.security.Principal;

import javax.ws.rs.core.SecurityContext;

class RestSecurityContextChecker implements SecuritySourceChecker {

    private final SecurityContext securityContext;

    RestSecurityContextChecker(SecurityContext securityContext) {
	this.securityContext = securityContext;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	try {
	    return securityContext.isUserInRole(securityRole.name());
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
}