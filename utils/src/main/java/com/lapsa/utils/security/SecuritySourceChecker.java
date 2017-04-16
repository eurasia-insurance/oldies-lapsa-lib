package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;

interface SecuritySourceChecker {
    boolean isUserInRole(SecurityRole securityRole);

    Principal getUserPrincipal();

    Locale getLocale();

    String getRemoteUser();
}
