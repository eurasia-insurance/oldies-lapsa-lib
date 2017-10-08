package com.lapsa.lapsa.velocitytools;

import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.runtime.RuntimeServices;
import org.apache.velocity.runtime.RuntimeSingleton;
import org.apache.velocity.runtime.parser.ParseException;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.util.ClassUtils;

public class DefaultLapsaVelocityTools implements LapsaVelocityTools {

    protected static final String DEFAULT_TEMPLATE_PATH = "emailTemplates";

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
    public VelocityContext configureDefaultVelocityContext(Locale locale) {
	VelocityContext context = new VelocityContext();

	// DateTool
	{
	    Java8TemporalTypesDateTool dt = new Java8TemporalTypesDateTool();
	    Map<String, Object> dtConf = new HashMap<>();
	    dtConf.put("format", "full_date");
	    dt.configure(dtConf);
	    context.put("date", dt);
	}

	// locale
	{
	    context.put("locale", locale);
	}

	return context;
    }

    @Override
    public String getTemplateReousrcePath(Locale locale, String templateResourceName) {
	String tag = (locale != null ? locale : Locale.getDefault()).getLanguage();
	return String.format("/%1$s/%2$s/%3$s", stripBothSlashes(templateResourcePath), tag,
		stripBeginingSlash(templateResourceName));
    }

    @Override
    public InputStream getTemplateReousrceAsStream(Locale locale, String templateResourceName) {
	String resourcePath = getTemplateReousrcePath(locale, templateResourceName);
	InputStream is = ClassUtils.getResourceAsStream(this.getClass(), resourcePath);
	if (is == null)
	    throw new RuntimeException(String.format("Resource not found '%1$s'", resourcePath));
	return is;
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale, String baseName) {
	ClassLoader classLoader = null;
	if (classLoader == null)
	    classLoader = Thread.currentThread().getContextClassLoader();
	if (classLoader == null)
	    classLoader = this.getClass().getClassLoader();
	if (classLoader == null)
	    throw new RuntimeException(
		    String.format("Resource bundle not found '%1$s' (Class loader is null)", baseName));
	ResourceBundle rb = ResourceBundle.getBundle(baseName, (locale != null ? locale : Locale.getDefault()),
		classLoader);
	if (rb == null)
	    throw new RuntimeException(String.format("Resource bundle not found '%1$s'", baseName));
	return rb;
    }

    @Override
    public String getTemplateMergedText(Locale locale, VelocityContext context,
	    String templateResourceName) throws TemplateException {
	String templatePath = getTemplateReousrcePath(locale, templateResourceName);

	Template t = getByPath(templatePath);

	Writer w = new StringWriter();
	try {
	    t.merge(context, w);
	} catch (ResourceNotFoundException | ParseErrorException | MethodInvocationException e) {
	    throw new TemplateException(e);
	}
	return w.toString();
    }

    @Override
    public String getTemplateMergedText(VelocityContext context, final String templateContent)
	    throws TemplateException {
	boolean originEndsWithLF = templateContent.endsWith("\n");
	String templ = originEndsWithLF ? templateContent : (templateContent + "\n");
	Template t = getFromString(templ);
	Writer w = new StringWriter();
	t.merge(context, w);
	StringBuffer sb = new StringBuffer(w.toString());
	if (!originEndsWithLF && sb.length() > 0)
	    sb.deleteCharAt(sb.length() - 1);
	return sb.toString();
    }

    // PRIVATE

    private Template getFromString(String templateContent) throws TemplateException {
	RuntimeServices runtimeServices = RuntimeSingleton.getRuntimeServices();
	StringReader reader = new StringReader(templateContent);
	SimpleNode node;
	try {
	    node = runtimeServices.parse(reader, "Template name");
	} catch (ParseException e) {
	    throw new TemplateException(e);
	}
	Template t = new Template();
	t.setRuntimeServices(runtimeServices);
	t.setData(node);
	t.initDocument();
	return t;
    }

    private Template getByPath(String templatePath) throws TemplateException {
	Template t;
	try {
	    t = velocityEngine.getTemplate(templatePath, "UTF-8");
	} catch (ResourceNotFoundException | ParseErrorException e) {
	    throw new TemplateException(e);
	}
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
