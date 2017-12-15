package com.lapsa.utils.security;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 993625936619403435L;

    public UnauthorizedException() {
	super();
    }

    public UnauthorizedException(final String message, final Throwable cause, final boolean enableSuppression,
	    final boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnauthorizedException(final String message, final Throwable cause) {
	super(message, cause);
    }

    public UnauthorizedException(final String message) {
	super(message);
    }

    public UnauthorizedException(final Throwable cause) {
	super(cause);
    }

}
