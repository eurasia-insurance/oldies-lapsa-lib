package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import javax.ejb.SessionContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

import com.lapsa.utils.UtilsMessage;

public final class SecurityUtils {

    public static String getRemoteUser(FacesContext facesContext) {
	return _getRemoteUser(new FacesContextChecker(facesContext));
    }

    public static String getRemoteUser(SessionContext ejbSessionContext, Locale locale) {
	return _getRemoteUser(new EJBSessionContextChecker(ejbSessionContext, locale));
    }

    public static String getRemoteUser(HttpServletRequest httpServletRequest) {
	return _getRemoteUser(new HttpServletRequestChecker(httpServletRequest));
    }

    public static String getRemoteUser(SecurityContext securityContext, Locale locale) {
	return _getRemoteUser(new RestSecurityContextChecker(securityContext, locale));
    }

    public static String getRemoteUser() {
	return _getRemoteUser(_determineChecker(Locale.getDefault()));
    }

    //

    public static Principal getUserPrincipal(FacesContext facesContext) {
	return _getUserPrincipal(new FacesContextChecker(facesContext));
    }

    public static Principal getUserPrincipal(SessionContext ejbSessionContext) {
	return _getUserPrincipal(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()));
    }

    public static Principal getUserPrincipal(HttpServletRequest httpServletRequest) {
	return _getUserPrincipal(new HttpServletRequestChecker(httpServletRequest));
    }

    public static Principal getUserPrincipal(SecurityContext securityContext) {
	return _getUserPrincipal(new RestSecurityContextChecker(securityContext, Locale.getDefault()));
    }

    public static Principal getUserPrincipal() {
	return _getUserPrincipal(_determineChecker(Locale.getDefault()));
    }

    //

    public static boolean isInRole(FacesContext facesContext, SecurityRoleGroup... roles) {
	return _isInRole(new FacesContextChecker(facesContext), roles);
    }

    public static boolean isInRole(SessionContext ejbSessionContext, SecurityRoleGroup... roles) {
	return _isInRole(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()), roles);
    }

    public static boolean isInRole(HttpServletRequest httpServletRequest, SecurityRoleGroup... roles) {
	return _isInRole(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static boolean isInRole(SecurityContext securityContext, SecurityRoleGroup... roles) {
	return _isInRole(new RestSecurityContextChecker(securityContext, Locale.getDefault()), roles);
    }

    public static boolean isInRole(SecurityRoleGroup... roles) {
	return _isInRole(_determineChecker(Locale.getDefault()), roles);
    }

    //

    public static void checkRoleGranted(FacesContext facesContext, String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleGranted(SessionContext ejbSessionContext, String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleGranted(HttpServletRequest httpServletRequest, String message,
	    SecurityRoleGroup... roles) {
	_checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleGranted(SecurityContext securityContext, String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(new RestSecurityContextChecker(securityContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleGranted(String message, SecurityRoleGroup... roles) {
	_checkRoleGranted(_determineChecker(Locale.getDefault()), message, roles);
    }

    //

    public static void checkRoleGranted(FacesContext facesContext, SecurityRoleGroup... roles) {
	_checkRoleGranted(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleGranted(SessionContext ejbSessionContext, Locale locale, SecurityRoleGroup... roles) {
	_checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext, locale), roles);
    }

    public static void checkRoleGranted(HttpServletRequest httpServletRequest, SecurityRoleGroup... roles) {
	_checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleGranted(SecurityContext securityContext, Locale locale, SecurityRoleGroup... roles) {
	_checkRoleGranted(new RestSecurityContextChecker(securityContext, locale), roles);
    }

    public static void checkRoleGranted(SecurityRoleGroup... roles) {
	_checkRoleGranted(_determineChecker(Locale.getDefault()), roles);
    }

    //

    public static void checkRoleDenied(FacesContext facesContext, String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleDenied(SessionContext ejbSessionContext, String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleDenied(HttpServletRequest httpServletRequest, String message,
	    SecurityRoleGroup... roles) {
	_checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleDenied(SecurityContext securityContext, String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(new RestSecurityContextChecker(securityContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleDenied(String message, SecurityRoleGroup... roles) {
	_checkRoleDenied(_determineChecker(Locale.getDefault()), message, roles);
    }

    //

    public static void checkRoleDenied(FacesContext facesContext, SecurityRoleGroup... roles) {
	_checkRoleDenied(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleDenied(SessionContext ejbSessionContext, Locale locale, SecurityRoleGroup... roles) {
	_checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext, locale), roles);
    }

    public static void checkRoleDenied(HttpServletRequest httpServletRequest, SecurityRoleGroup... roles) {
	_checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleDenied(SecurityContext securityContext, Locale locale, SecurityRoleGroup... roles) {
	_checkRoleDenied(new RestSecurityContextChecker(securityContext, locale), roles);
    }

    public static void checkRoleDenied(Locale locale, SecurityRoleGroup... roles) {
	_checkRoleDenied(_determineChecker(locale), roles);
    }

    // PRIVATE

    private static SecuritySourceChecker _determineChecker(Locale locale) {
	try {
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (facesContext != null)
		return new FacesContextChecker(facesContext);
	} catch (Exception ignored) {
	}

	throw new RuntimeException("Can not determine context");
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
	ResourceBundle bundle = ResourceBundle.getBundle(UtilsMessage.BUNDLE_BASENAME, checker.getLocale());
	String prefix = bundle.getString(UtilsMessage.SECURITY_ROLES_GRANTED_PREFIX.canonicalName());
	String suffix = bundle.getString(UtilsMessage.SECURITY_ROLES_GRANTED_SUFFIX.canonicalName());
	StringJoiner sj = new StringJoiner(", ", prefix, suffix);
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
	ResourceBundle bundle = ResourceBundle.getBundle(UtilsMessage.BUNDLE_BASENAME, checker.getLocale());
	String prefix = bundle.getString(UtilsMessage.SECURITY_ROLES_DENIED_PREFIX.canonicalName());
	String suffix = bundle.getString(UtilsMessage.SECURITY_ROLES_DENIED_SUFFIX.canonicalName());
	StringJoiner sj = new StringJoiner(", ", prefix, suffix);
	for (SecurityRoleGroup g : groups)
	    sj.add(g.toString());
	_checkRoleDenied(checker, sj.toString(), groups);
    }

}
