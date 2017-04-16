package com.lapsa.utils.security;

import java.security.Principal;
import java.util.StringJoiner;

import javax.ejb.SessionContext;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

public final class SecurityUtils {

    public static String getRemoteUser(FacesContext facesContext) {
	return _getRemoteUser(new FacesContextChecker(facesContext));
    }

    public static String getRemoteUser(SessionContext ejbSessionContext) {
	return _getRemoteUser(new EJBSessionContextChecker(ejbSessionContext));
    }

    public static String getRemoteUser(HttpServletRequest httpServletRequest) {
	return _getRemoteUser(new HttpServletRequestChecker(httpServletRequest));
    }

    public static String getRemoteUser(SecurityContext securityContext) {
	return _getRemoteUser(new RestSecurityContextChecker(securityContext));
    }

    public static String getRemoteUser() {
	return _getRemoteUser(_determineChecker());
    }

    //

    public static Principal getUserPrincipal(FacesContext facesContext) {
	return _getUserPrincipal(new FacesContextChecker(facesContext));
    }

    public static Principal getUserPrincipal(SessionContext ejbSessionContext) {
	return _getUserPrincipal(new EJBSessionContextChecker(ejbSessionContext));
    }

    public static Principal getUserPrincipal(HttpServletRequest httpServletRequest) {
	return _getUserPrincipal(new HttpServletRequestChecker(httpServletRequest));
    }

    public static Principal getUserPrincipal(SecurityContext securityContext) {
	return _getUserPrincipal(new RestSecurityContextChecker(securityContext));
    }

    public static Principal getUserPrincipal() {
	return _getUserPrincipal(_determineChecker());
    }

    //

    public static boolean isInRole(FacesContext facesContext, SecurityRoleGroup... roles) {
	return _isInRole(new FacesContextChecker(facesContext), roles);
    }

    public static boolean isInRole(SessionContext ejbSessionContext, SecurityRoleGroup... roles) {
	return _isInRole(new EJBSessionContextChecker(ejbSessionContext), roles);
    }

    public static boolean isInRole(HttpServletRequest httpServletRequest, SecurityRoleGroup... roles) {
	return _isInRole(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static boolean isInRole(SecurityContext securityContext, SecurityRoleGroup... roles) {
	return _isInRole(new RestSecurityContextChecker(securityContext), roles);
    }

    public static boolean isInRole(SecurityRoleGroup... roles) {
	return _isInRole(_determineChecker(), roles);
    }

    //

    public static void checkRoleGranted(FacesContext facesContext, String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleGranted(SessionContext ejbSessionContext, String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext), message, roles);
    }

    public static void checkRoleGranted(HttpServletRequest httpServletRequest, String message,
	    SecurityRoleGroup... roles) {
	_checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleGranted(SecurityContext securityContext, String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(new RestSecurityContextChecker(securityContext), message, roles);
    }

    public static void checkRoleGranted(String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(_determineChecker(), message, roles);
    }

    //

    public static void checkRoleGranted(FacesContext facesContext, SecurityRoleGroup... roles) {
	_checkRoleGranted(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleGranted(SessionContext ejbSessionContext, SecurityRoleGroup... roles) {
	_checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext), roles);
    }

    public static void checkRoleGranted(HttpServletRequest httpServletRequest, SecurityRoleGroup... roles) {
	_checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleGranted(SecurityContext securityContext, SecurityRoleGroup... roles) {
	_checkRoleGranted(new RestSecurityContextChecker(securityContext), roles);
    }

    public static void checkRoleGranted(SecurityRoleGroup... roles) {
	_checkRoleGranted(_determineChecker(), roles);
    }

    //

    public static void checkRoleDenied(FacesContext facesContext, String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleDenied(SessionContext ejbSessionContext, String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext), message, roles);
    }

    public static void checkRoleDenied(HttpServletRequest httpServletRequest, String message,
	    SecurityRoleGroup... roles) {
	_checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleDenied(SecurityContext securityContext, String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(new RestSecurityContextChecker(securityContext), message, roles);
    }

    public static void checkRoleDenied(String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(_determineChecker(), message, roles);
    }

    //

    public static void checkRoleDenied(FacesContext facesContext, SecurityRoleGroup... roles) {
	_checkRoleDenied(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleDenied(SessionContext ejbSessionContext, SecurityRoleGroup... roles) {
	_checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext), roles);
    }

    public static void checkRoleDenied(HttpServletRequest httpServletRequest, SecurityRoleGroup... roles) {
	_checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleDenied(SecurityContext securityContext, SecurityRoleGroup... roles) {
	_checkRoleDenied(new RestSecurityContextChecker(securityContext), roles);
    }

    public static void checkRoleDenied(SecurityRoleGroup... roles) {
	_checkRoleDenied(_determineChecker(), roles);
    }

    // PRIVATE

    private static SecuritySourceChecker _determineChecker() {
	try {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (facesContext != null)
		return new FacesContextChecker(facesContext);
	} catch (Exception ignored) {
	}

	try {
	    SessionContext ejbSessionContext = CDI.current().select(SessionContext.class).get();
	    if (ejbSessionContext != null)
		return new EJBSessionContextChecker(ejbSessionContext);
	} catch (Exception ignored) {
	}

	try {
	    SecurityContext restSecurityContext = CDI.current().select(SecurityContext.class).get();
	    if (restSecurityContext != null)
		return new RestSecurityContextChecker(restSecurityContext);
	} catch (Exception ignored) {
	}

	try {
	    HttpServletRequest httpServletRequest = CDI.current().select(HttpServletRequest.class).get();
	    if (httpServletRequest != null)
		return new HttpServletRequestChecker(httpServletRequest);
	} catch (Exception ignored) {
	}

	throw new RuntimeException("Can not determine security context");
    }

    private static String _getRemoteUser(SecuritySourceChecker checker) {
	return checker.getRemoteUser();
    }

    private static Principal _getUserPrincipal(SecuritySourceChecker determineChecker) {
	return determineChecker.getUserPrincipal();
    }

    private static boolean _isInRole(SecuritySourceChecker securitySourceChecker, SecurityRoleGroup... roles) {
	for (SecurityRoleGroup g : roles)
	    for (SecurityRole r : g.getRoles())
		if (securitySourceChecker.isUserInRole(r))
		    return true;
	return false;
    }

    private static void _checkRoleGranted(SecuritySourceChecker checker, String message, SecurityRoleGroup[] roles) {
	if (_isInRole(checker, roles))
	    return;
	throw new UnauthorizedException(message);
    }

    private static void _checkRoleGranted(SecuritySourceChecker checker, SecurityRoleGroup... groups) {
	StringJoiner sj = new StringJoiner(", ",
		"Недостаточно прав доступа. Требуется как минимум одна из следующих ролей доступа: ", ".");
	for (SecurityRoleGroup g : groups)
	    sj.add(g.toString());
	_checkRoleGranted(checker, sj.toString(), groups);
    }

    private static void _checkRoleDenied(SecuritySourceChecker checker, String message, SecurityRoleGroup... roles) {
	if (!_isInRole(checker, roles))
	    return;
	throw new UnauthorizedException(message);
    }

    private static void _checkRoleDenied(SecuritySourceChecker checker, SecurityRoleGroup... groups) {
	StringJoiner sj = new StringJoiner(", ",
		"Недостаточно прав доступа. Доступ для ролей: ", "запрещен.");
	for (SecurityRoleGroup g : groups)
	    sj.add(g.toString());
	_checkRoleDenied(checker, sj.toString(), groups);
    }

}
