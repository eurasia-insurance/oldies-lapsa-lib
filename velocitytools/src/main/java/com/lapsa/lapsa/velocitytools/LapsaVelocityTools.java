package com.lapsa.lapsa.velocitytools;

import java.io.InputStream;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.velocity.VelocityContext;

public interface LapsaVelocityTools {

    VelocityContext configureDefaultVelocityContext(Locale language);

    String getTemplateReousrcePath(Locale language, String templateResourceName);

    void setDefaultTemplateResourcePath(String templateResourcePath);

    String getTemplateMergedText(Locale language, VelocityContext context, String templateResourceName)
	    throws TemplateException;

    String getTemplateMergedText(VelocityContext context, String templateContent) throws TemplateException;

    InputStream getTemplateReousrceAsStream(Locale language, String templateResourceName);

    ResourceBundle getResourceBundle(Locale language, String bundleBasename);

}