package com.lapsa.utils.security;

import java.security.Principal;

interface SecuritySourceChecker {
    boolean isUserInRole(SecurityRole securityRole);

    Principal getUserPrincipal();

    String getRemoteUser();
}
