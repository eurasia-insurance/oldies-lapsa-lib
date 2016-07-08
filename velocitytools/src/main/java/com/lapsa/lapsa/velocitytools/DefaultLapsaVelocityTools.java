package com.lapsa.lapsa.velocitytools;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.tools.generic.DateTool;

import com.lapsa.localization.LocalizationLanguage;

public class DefaultLapsaVelocityTools implements LapsaVelocityTools {

    protected static final String DEFAULT_TEMPLATE_PATH = "/emailTemplates/";

    private String templateResourcePath = DEFAULT_TEMPLATE_PATH;

    @Inject
    protected VelocityEngine velocityEngine;

    @Inject
    protected Logger logger;

    @Override
    public void setDefaultTemplateResourcePath(String templateResourcePath) {
	this.templateResourcePath = templateResourcePath;
    }

    @Override
    public VelocityContext configureDefaultVelocityContext(LocalizationLanguage language) {
	VelocityContext context = new VelocityContext();

	// DateTool
	{
	    DateTool dt = new DateTool();
	    Map<String, Object> dtConf = new HashMap<>();
	    dtConf.put("format", "full_date");
	    dtConf.put("locale", language.getLocale());
	    dt.configure(dtConf);
	    context.put("date", dt);
	}

	// language
	{
	    context.put("language", language);
	}

	return context;
    }

    @Override
    public String getTemplateReousrcePath(LocalizationLanguage language, String templateResourceName) {
	return String.format("%1$s/%2$s/%3$s", templateResourcePath, language.getTag(), templateResourceName);
    }

    @Override
    public InputStream getTemplateReousrceAsStream(LocalizationLanguage language, String templateResourceName) {
	return DefaultLapsaVelocityTools.class
		.getResourceAsStream(getTemplateReousrcePath(language, templateResourceName));
    }

    @Override
    public String getTemplateMergedText(VelocityContext context, LocalizationLanguage language,
	    String templateResourceName) {
	String templatePath = getTemplateReousrcePath(language, templateResourceName);

	Template t = getByPath(templatePath);

	Writer w = new StringWriter();
	t.merge(context, w);
	return w.toString();
    }

    @Override
    public String getTemplateMergedText(VelocityContext context, final String templateContent)
	    throws ParseException {
	boolean originEndsWithCR = templateContent.endsWith("\n");
	String templ = (originEndsWithCR) ? templateContent : (templateContent + "\n");
	Template t = getFromString(templ);
	Writer w = new StringWriter();
	t.merge(context, w);
	StringBuffer sb = new StringBuffer(w.toString());
	if (!originEndsWithCR && sb.length() > 0)
	    sb.deleteCharAt(sb.length() - 1);
	return sb.toString();
    }

    // PRIVATE

    private Template getFromString(String templateContent) throws ParseException {
	RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
	StringReader reader = new StringReader(templateContent);
	SimpleNode node = runtimeServices.parse(reader, "Template name");
	Template t = new Template();
	t.setRuntimeServices(runtimeServices);
	t.setData(node);
	t.initDocument();
	return t;
    }

    private Template getByPath(String templatePath) {
	Template t = velocityEngine.getTemplate(templatePath, "UTF-8");
	return t;
    }

}
