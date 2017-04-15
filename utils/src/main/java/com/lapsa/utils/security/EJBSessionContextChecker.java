package com.lapsa.utils.security;

import javax.ejb.SessionContext;

class EJBSessionContextChecker implements RoleChecker {

    private final SessionContext ejbSessionContext;

    EJBSessionContextChecker(SessionContext ejbSessionContext) {
	this.ejbSessionContext = ejbSessionContext;
    }

    @Override
    public boolean isUserInRole(SecurityRole securityRole) {
	return ejbSessionContext.isCallerInRole(securityRole.name());
    }
}