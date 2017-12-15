package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;

import javax.faces.context.FacesContext;

class FacesContextChecker implements SecuritySourceChecker {

    private final FacesContext facesContext;

    FacesContextChecker(final FacesContext facesContext) {
	this.facesContext = facesContext;
    }

    @Override
    public boolean isUserInRole(final SecurityRole securityRole) {
	try {
	    return facesContext.getExternalContext().isUserInRole(securityRole.roleName());
	} catch (IllegalStateException | NullPointerException e) {
	    return false;
	}
    }

    @Override
    public Principal getUserPrincipal() {
	try {
	    return facesContext.getExternalContext().getUserPrincipal();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public String getRemoteUser() {
	try {
	    return facesContext.getExternalContext().getRemoteUser();
	} catch (IllegalStateException | NullPointerException e) {
	    return null;
	}
    }

    @Override
    public Locale getLocale() {
	return facesContext.getExternalContext().getRequestLocale();
    }
}