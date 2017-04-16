package com.lapsa.utils.security;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 993625936619403435L;

    public UnauthorizedException() {
	super();
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression,
	    boolean writableStackTrace) {
	super(message, cause, enableSuppression, writableStackTrace);
    }

    public UnauthorizedException(String message, Throwable cause) {
	super(message, cause);
    }

    public UnauthorizedException(String message) {
	super(message);
    }

    public UnauthorizedException(Throwable cause) {
	super(cause);
    }

}
