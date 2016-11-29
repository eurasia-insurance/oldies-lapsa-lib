package com.lapsa.lapsa.cdiutils.producers;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Interceptor
@Loggable
public class LoggingInterceptor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Logger logger;

    @AroundInvoke
    private Object logMethod(InvocationContext ic) throws Exception {
	Object[] params = ic.getParameters();
	StringBuffer parstr = new StringBuffer();
	for (int i = 0; i < params.length; i++) {
	    if (i > 0)
		parstr.append(", ");
	    parstr.append(String.format("\"%1$s\"", params[i]));
	}
	logger.info(String.format("%1$s.%2$s(%3$s)", ic.getTarget().toString(), ic.getMethod().getName(),
		parstr.toString()));
	return ic.proceed();
    }
}
