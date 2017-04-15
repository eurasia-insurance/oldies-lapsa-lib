package com.lapsa.utils.security;

interface RoleChecker {
    boolean isUserInRole(SecurityRole securityRole);
}
