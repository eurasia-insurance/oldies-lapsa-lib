package com.lapsa.utils;

public enum UtilsMessage implements ElementsBundleBase {
    SECURITY_ROLES_GRANTED_PREFIX,
    SECURITY_ROLES_GRANTED_SUFFIX,
    SECURITY_ROLES_DENIED_PREFIX,
    SECURITY_ROLES_DENIED_SUFFIX;

    @Override
    public String toString() {
	return canonicalName();
    }

    @Override
    public String canonicalName() {
	return String.format("%1$s.%2$s", this.getClass().getName(), name());
    }
}
