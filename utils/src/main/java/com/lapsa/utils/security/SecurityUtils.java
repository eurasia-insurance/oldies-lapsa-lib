package com.lapsa.utils.security;

import java.security.Principal;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.StringJoiner;

import javax.ejb.SessionContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

import com.lapsa.utils.ElementsBundleBase;
import com.lapsa.utils.UtilsMessage;

public final class SecurityUtils {

    public static String getRemoteUser(final FacesContext facesContext) {
	return _getRemoteUser(new FacesContextChecker(facesContext));
    }

    public static String getRemoteUser(final SessionContext ejbSessionContext, final Locale locale) {
	return _getRemoteUser(new EJBSessionContextChecker(ejbSessionContext, locale));
    }

    public static String getRemoteUser(final HttpServletRequest httpServletRequest) {
	return _getRemoteUser(new HttpServletRequestChecker(httpServletRequest));
    }

    public static String getRemoteUser(final SecurityContext securityContext, final Locale locale) {
	return _getRemoteUser(new RestSecurityContextChecker(securityContext, locale));
    }

    public static String getRemoteUser() {
	return _getRemoteUser(_determineChecker(Locale.getDefault()));
    }

    //

    public static Principal getUserPrincipal(final FacesContext facesContext) {
	return _getUserPrincipal(new FacesContextChecker(facesContext));
    }

    public static Principal getUserPrincipal(final SessionContext ejbSessionContext) {
	return _getUserPrincipal(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()));
    }

    public static Principal getUserPrincipal(final HttpServletRequest httpServletRequest) {
	return _getUserPrincipal(new HttpServletRequestChecker(httpServletRequest));
    }

    public static Principal getUserPrincipal(final SecurityContext securityContext) {
	return _getUserPrincipal(new RestSecurityContextChecker(securityContext, Locale.getDefault()));
    }

    public static Principal getUserPrincipal() {
	return _getUserPrincipal(_determineChecker(Locale.getDefault()));
    }

    //

    public static boolean isInRole(final FacesContext facesContext, final SecurityRoleGroup... roles) {
	return _isInRole(new FacesContextChecker(facesContext), roles);
    }

    public static boolean isInRole(final SessionContext ejbSessionContext, final SecurityRoleGroup... roles) {
	return _isInRole(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()), roles);
    }

    public static boolean isInRole(final HttpServletRequest httpServletRequest, final SecurityRoleGroup... roles) {
	return _isInRole(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static boolean isInRole(final SecurityContext securityContext, final SecurityRoleGroup... roles) {
	return _isInRole(new RestSecurityContextChecker(securityContext, Locale.getDefault()), roles);
    }

    public static boolean isInRole(final SecurityRoleGroup... roles) {
	return _isInRole(_determineChecker(Locale.getDefault()), roles);
    }

    //

    public static void checkRoleGranted(final FacesContext facesContext, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleGranted(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleGranted(final SessionContext ejbSessionContext, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleGranted(final HttpServletRequest httpServletRequest, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleGranted(final SecurityContext securityContext, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleGranted(new RestSecurityContextChecker(securityContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleGranted(final String message, final SecurityRoleGroup... roles) {
	_checkRoleGranted(_determineChecker(Locale.getDefault()), message, roles);
    }

    //

    public static void checkRoleGranted(final FacesContext facesContext, final SecurityRoleGroup... roles) {
	_checkRoleGranted(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleGranted(final SessionContext ejbSessionContext, final Locale locale,
	    final SecurityRoleGroup... roles) {
	_checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext, locale), roles);
    }

    public static void checkRoleGranted(final HttpServletRequest httpServletRequest, final SecurityRoleGroup... roles) {
	_checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleGranted(final SecurityContext securityContext, final Locale locale,
	    final SecurityRoleGroup... roles) {
	_checkRoleGranted(new RestSecurityContextChecker(securityContext, locale), roles);
    }

    public static void checkRoleGranted(final SecurityRoleGroup... roles) {
	_checkRoleGranted(_determineChecker(Locale.getDefault()), roles);
    }

    //

    public static void checkRoleDenied(final FacesContext facesContext, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleDenied(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleDenied(final SessionContext ejbSessionContext, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleDenied(final HttpServletRequest httpServletRequest, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleDenied(final SecurityContext securityContext, final String message,
	    final SecurityRoleGroup... roles) {
	_checkRoleDenied(new RestSecurityContextChecker(securityContext, Locale.getDefault()), message, roles);
    }

    public static void checkRoleDenied(final String message, final SecurityRoleGroup... roles) {
	_checkRoleDenied(_determineChecker(Locale.getDefault()), message, roles);
    }

    //

    public static void checkRoleDenied(final FacesContext facesContext, final SecurityRoleGroup... roles) {
	_checkRoleDenied(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleDenied(final SessionContext ejbSessionContext, final Locale locale,
	    final SecurityRoleGroup... roles) {
	_checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext, locale), roles);
    }

    public static void checkRoleDenied(final HttpServletRequest httpServletRequest, final SecurityRoleGroup... roles) {
	_checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleDenied(final SecurityContext securityContext, final Locale locale,
	    final SecurityRoleGroup... roles) {
	_checkRoleDenied(new RestSecurityContextChecker(securityContext, locale), roles);
    }

    public static void checkRoleDenied(final Locale locale, final SecurityRoleGroup... roles) {
	_checkRoleDenied(_determineChecker(locale), roles);
    }

    // PRIVATE

    private static SecuritySourceChecker _determineChecker(final Locale locale) {
	try {
	    final FacesContext facesContext = FacesContext.getCurrentInstance();
	    if (facesContext != null)
		return new FacesContextChecker(facesContext);
	} catch (final Exception ignored) {
	}

	throw new RuntimeException("Can not determine context");
    }

    private static String _getRemoteUser(final SecuritySourceChecker checker) {
	return checker.getRemoteUser();
    }

    private static Principal _getUserPrincipal(final SecuritySourceChecker determineChecker) {
	return determineChecker.getUserPrincipal();
    }

    private static boolean _isInRole(final SecuritySourceChecker securitySourceChecker,
	    final SecurityRoleGroup... roles) {
	for (final SecurityRoleGroup g : roles)
	    for (final SecurityRole r : g.getRoles())
		if (securitySourceChecker.isUserInRole(r))
		    return true;
	return false;
    }

    private static void _checkRoleGranted(final SecuritySourceChecker checker, final String message,
	    final SecurityRoleGroup[] roles) {
	if (_isInRole(checker, roles))
	    return;
	throw new UnauthorizedException(message);
    }

    private static void _checkRoleGranted(final SecuritySourceChecker checker, final SecurityRoleGroup... groups) {
	final ResourceBundle bundle = ResourceBundle.getBundle(ElementsBundleBase.BUNDLE_BASENAME, checker.getLocale());
	final String prefix = bundle.getString(UtilsMessage.SECURITY_ROLES_GRANTED_PREFIX.canonicalName());
	final String suffix = bundle.getString(UtilsMessage.SECURITY_ROLES_GRANTED_SUFFIX.canonicalName());
	final StringJoiner sj = new StringJoiner(", ", prefix, suffix);
	for (final SecurityRoleGroup g : groups)
	    sj.add(g.toString());
	_checkRoleGranted(checker, sj.toString(), groups);
    }

    private static void _checkRoleDenied(final SecuritySourceChecker checker, final String message,
	    final SecurityRoleGroup... roles) {
	if (!_isInRole(checker, roles))
	    return;
	throw new UnauthorizedException(message);
    }

    private static void _checkRoleDenied(final SecuritySourceChecker checker, final SecurityRoleGroup... groups) {
	final ResourceBundle bundle = ResourceBundle.getBundle(ElementsBundleBase.BUNDLE_BASENAME, checker.getLocale());
	final String prefix = bundle.getString(UtilsMessage.SECURITY_ROLES_DENIED_PREFIX.canonicalName());
	final String suffix = bundle.getString(UtilsMessage.SECURITY_ROLES_DENIED_SUFFIX.canonicalName());
	final StringJoiner sj = new StringJoiner(", ", prefix, suffix);
	for (final SecurityRoleGroup g : groups)
	    sj.add(g.toString());
	_checkRoleDenied(checker, sj.toString(), groups);
    }

}
