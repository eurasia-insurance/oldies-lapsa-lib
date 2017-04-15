package com.lapsa.utils.security;

import javax.servlet.http.HttpServletRequest;

class HttpServletRequestChecker implements RoleChecker {

    private final HttpServletRequest httpServletRequest;

    HttpServletRequestChecker(HttpServletRequest httpServletRequest) {
	this.httpServletRequest = httpServletRequest;
    }

    @Override
    public boolean isUserInRole(Role role) {
	return httpServletRequest.isUserInRole(role.name());
    }
}