package com.lapsa.utils.security;

public interface SecurityRole extends SecurityRoleGroup {

    String roleName();

    @Override
    default SecurityRole[] getRoles() {
	return new SecurityRole[] { this };
    }
}
