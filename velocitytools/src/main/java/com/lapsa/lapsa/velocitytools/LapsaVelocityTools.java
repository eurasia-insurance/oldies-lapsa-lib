package com.lapsa.lapsa.velocitytools;

import java.io.InputStream;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.runtime.parser.ParseException;

import com.lapsa.localization.LocalizationLanguage;

public interface LapsaVelocityTools {

    VelocityContext configureDefaultVelocityContext(LocalizationLanguage language);

    String getTemplateReousrcePath(LocalizationLanguage language,
	    String templateResourceName);

    void setDefaultTemplateResourcePath(String templateResourcePath);
    
    String getTemplateMergedText(VelocityContext context, LocalizationLanguage language,
	    String templateResourceName);

    String getTemplateMergedText(VelocityContext context, String templateContent)
	    throws ParseException;

    InputStream getTemplateReousrceAsStream(ClassLoader classLoader, LocalizationLanguage language, String templateResourceName);

}