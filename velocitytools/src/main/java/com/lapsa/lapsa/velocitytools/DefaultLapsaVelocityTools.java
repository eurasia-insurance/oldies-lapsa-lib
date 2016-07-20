package com.lapsa.lapsa.velocitytools;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
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

    protected static final String DEFAULT_TEMPLATE_PATH = "emailTemplates";

    private String templateResourcePath = DEFAULT_TEMPLATE_PATH;

    @Inject
    protected VelocityEngine velocityEngine;

    @Inject
    protected Logger logger;

    @Override
    public void setDefaultTemplateResourcePath(String templateResourcePath) {
	this.templateResourcePath =  templateResourcePath;
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
	return String.format("/%1$s/%2$s/%3$s", stripBothSlashes(templateResourcePath), language.getTag(),
		stripBeginingSlash(templateResourceName));
    }

    @Override
    public InputStream getTemplateReousrceAsStream(Class<?> clazz, LocalizationLanguage language,
	    String templateResourceName) {
	String resourcePath = getTemplateReousrcePath(language, templateResourceName);
	InputStream is = clazz.getResourceAsStream(resourcePath);
	if (is == null)
	    throw new RuntimeException(String.format("Resource not found '%1$s'", resourcePath));
	return is;
    }

    @Override
    public ResourceBundle getResourceBundle(String baseName, LocalizationLanguage language, Class<?> clazz) {
	ClassLoader classLoader = null;
	if (classLoader == null)
	    classLoader = Thread.currentThread().getContextClassLoader();
	if (classLoader == null)
	    classLoader = clazz.getClassLoader();
	if (classLoader == null)
	    throw new RuntimeException(
		    String.format("Resource bundle not found '%1$s' (Class loader is null)", baseName));
	ResourceBundle rb = ResourceBundle.getBundle(baseName, language.getLocale(), classLoader);
	if (rb == null)
	    throw new RuntimeException(String.format("Resource bundle not found '%1$s'", baseName));
	return rb;
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

    private String stripBothSlashes(String value) {
	return stripTailingSlash(stripBeginingSlash(value));
    }

    private String stripTailingSlash(String value) {
	StringBuffer ret = new StringBuffer(value);
	while (ret.charAt(ret.length() - 1) == '/')
	    ret.deleteCharAt(ret.length() - 1);
	return ret.toString();
    }

    private String stripBeginingSlash(String value) {
	StringBuffer ret = new StringBuffer(value);
	while (ret.charAt(0) == '/')
	    ret.deleteCharAt(0);
	return ret.toString();
    }

}
