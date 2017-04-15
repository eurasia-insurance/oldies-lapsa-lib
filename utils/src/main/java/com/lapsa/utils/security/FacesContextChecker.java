package com.lapsa.utils.security;

import javax.faces.context.FacesContext;

class FacesContextChecker implements RoleChecker {

    private final FacesContext facesContext;

    FacesContextChecker(FacesContext facesContext) {
	this.facesContext = facesContext;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	return facesContext.getExternalContext().isUserInRole(securityRole.name());
    }
}