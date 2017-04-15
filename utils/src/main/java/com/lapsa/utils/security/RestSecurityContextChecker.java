package com.lapsa.utils.security;

import javax.ws.rs.core.SecurityContext;

class RestSecurityContextChecker implements RoleChecker {

    private final SecurityContext securityContext;

    RestSecurityContextChecker(SecurityContext securityContext) {
	this.securityContext = securityContext;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	return securityContext.isUserInRole(securityRole.name());
    }
}