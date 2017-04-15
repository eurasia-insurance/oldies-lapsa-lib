package com.lapsa.utils.security;

import javax.ejb.SessionContext;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.SecurityContext;

public final class SecurityUtils {

    public static boolean isInRole(FacesContext facesContext, RoleGroup... roles) {
	return isInRole(new FacesContextChecker(facesContext), roles);
    }

    public static boolean isInRole(SessionContext ejbSessionContext, RoleGroup... roles) {
	return isInRole(new EJBSessionContextChecker(ejbSessionContext), roles);
    }

    public static boolean isInRole(HttpServletRequest httpServletRequest, RoleGroup... roles) {
	return isInRole(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static boolean isInRole(SecurityContext securityContext, RoleGroup... roles) {
	return isInRole(new RestSecurityContextChecker(securityContext), roles);
    }

    public static boolean isInRole(RoleGroup... roles) {
	RoleChecker checker = determineChecker();
	return isInRole(checker);
    }

    //

    public static void checkRoleGranted(FacesContext facesContext, String message, RoleGroup... roles) {
	checkRoleGranted(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleGranted(SessionContext ejbSessionContext, String message, RoleGroup... roles) {
	checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext), message, roles);
    }

    public static void checkRoleGranted(HttpServletRequest httpServletRequest, String message, RoleGroup... roles) {
	checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleGranted(SecurityContext securityContext, String message, RoleGroup... roles) {
	checkRoleGranted(new RestSecurityContextChecker(securityContext), message, roles);
    }

    public static void checkRoleGranted(String message, RoleGroup... roles) {
	RoleChecker checker = determineChecker();
	checkRoleGranted(checker, message, roles);
    }

    //

    public static void checkRoleGranted(FacesContext facesContext, RoleGroup... roles) {
	checkRoleGranted(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleGranted(SessionContext ejbSessionContext, RoleGroup... roles) {
	checkRoleGranted(new EJBSessionContextChecker(ejbSessionContext), roles);
    }

    public static void checkRoleGranted(HttpServletRequest httpServletRequest, RoleGroup... roles) {
	checkRoleGranted(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleGranted(SecurityContext securityContext, RoleGroup... roles) {
	checkRoleGranted(new RestSecurityContextChecker(securityContext), roles);
    }

    public static void checkRoleGranted(RoleGroup... roles) {
	RoleChecker checker = determineChecker();
	checkRoleGranted(checker, roles);
    }

    //

    public static void checkRoleDenied(FacesContext facesContext, String message, RoleGroup... roles) {
	checkRoleDenied(new FacesContextChecker(facesContext), message, roles);
    }

    public static void checkRoleDenied(SessionContext ejbSessionContext, String message, RoleGroup... roles) {
	checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext), message, roles);
    }

    public static void checkRoleDenied(HttpServletRequest httpServletRequest, String message, RoleGroup... roles) {
	checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), message, roles);
    }

    public static void checkRoleDenied(SecurityContext securityContext, String message, RoleGroup... roles) {
	checkRoleDenied(new RestSecurityContextChecker(securityContext), message, roles);
    }

    public static void checkRoleDenied(String message, RoleGroup... roles) {
	RoleChecker checker = determineChecker();
	checkRoleDenied(checker, message, roles);
    }

    //

    public static void checkRoleDenied(FacesContext facesContext, RoleGroup... roles) {
	checkRoleDenied(new FacesContextChecker(facesContext), roles);
    }

    public static void checkRoleDenied(SessionContext ejbSessionContext, RoleGroup... roles) {
	checkRoleDenied(new EJBSessionContextChecker(ejbSessionContext), roles);
    }

    public static void checkRoleDenied(HttpServletRequest httpServletRequest, RoleGroup... roles) {
	checkRoleDenied(new HttpServletRequestChecker(httpServletRequest), roles);
    }

    public static void checkRoleDenied(SecurityContext securityContext, RoleGroup... roles) {
	checkRoleDenied(new RestSecurityContextChecker(securityContext), roles);
    }

    public static void checkRoleDenied(RoleGroup... roles) {
	RoleChecker checker = determineChecker();
	checkRoleDenied(checker, roles);
    }

    // PRIVATE

    private static RoleChecker determineChecker() {
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

    private static boolean isInRole(RoleChecker roleChecker, RoleGroup... roles) {
	for (RoleGroup roleGroup : roles)
	    for (Role r : roleGroup.getRoles())
		if (roleChecker.isUserInRole(r))
		    return true;
	return false;
    }

    private static void checkRoleGranted(RoleChecker checker, String message, RoleGroup[] roles) {
	if (isInRole(checker, roles))
	    return;
	throw new UnauthorizedException(message);
    }

    private static void checkRoleGranted(RoleChecker checker, RoleGroup... roles) {
	StringBuffer sb = new StringBuffer("Недостаточно прав доступа.");
	for (int i = 0; i < roles.length; i++) {
	    if (i == 0)
		sb.append(" Требуется как минимум одна из следующих ролей доступа: ");
	    sb.append(roles[i].toString());
	    if (i == roles.length - 1)
		sb.append(".");
	    else
		sb.append(", ");
	}
	checkRoleGranted(checker, sb.toString(), roles);
    }

    private static void checkRoleDenied(RoleChecker checker, String message, RoleGroup... roles) {
	if (!isInRole(checker, roles))
	    return;
	throw new UnauthorizedException(message);
    }

    private static void checkRoleDenied(RoleChecker checker, RoleGroup... roles) {
	StringBuffer sb = new StringBuffer("Недостаточно прав доступа.");
	for (int i = 0; i < roles.length; i++) {
	    if (i == 0)
		sb.append(" Доступ для ролей : ");
	    sb.append(roles[i].toString());
	    if (i == roles.length - 1)
		sb.append(" запрещен.");
	    else
		sb.append(", ");
	}
	checkRoleDenied(checker, sb.toString(), roles);
    }

}
