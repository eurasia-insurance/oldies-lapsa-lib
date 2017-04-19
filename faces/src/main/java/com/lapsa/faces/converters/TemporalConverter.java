package com.lapsa.faces.converters;

import java.time.DateTimeException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.util.MessageFactory;

public abstract class TemporalConverter<T> implements Converter {

    private final Class<T> clazz;

    protected TemporalConverter(Class<T> clazz) {
	this.clazz = clazz;
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	if (value == null)
	    return null;

	if (value.trim().isEmpty())
	    return null;

	DateTimeFormatter df = getDefaultFormatter();
	if (component instanceof Calendar) {
	    Calendar cal = (Calendar) component;
	    df = patternOrDefault(cal.getPattern());
	}
	try {
	    return parse(value, df);
	} catch (DateTimeParseException e) {
	    throw new ConverterException(formatErrorMessage(getWich(), context, component, value), e);
	}
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
	if (value == null)
	    return null;
	
	if (value instanceof String)
	    return (String) value;

	T dateValue;
	try {
	    dateValue = clazz.cast(value);
	} catch (ClassCastException e) {
	    throw new ConverterException(formatErrorMessage(getWich(), context, component, value), e);
	}

	DateTimeFormatter df = getDefaultFormatter();
	if (component instanceof Calendar) {
	    Calendar cal = (Calendar) component;
	    df = patternOrDefault(cal.getPattern());
	}

	try {
	    String res = format(dateValue, df);
	    primefacesSupport(component, res);
	    return res;
	} catch (DateTimeException e) {
	    throw new ConverterException(formatErrorMessage(getWich(), context, component, value), e);
	}
    }

    private static FacesMessage formatErrorMessage(String which, FacesContext context, UIComponent component,
	    Object value) {
	return new FacesMessage(FacesMessage.SEVERITY_ERROR, String.format("%s Conversion Failed: %s - %s", which,
		(String) MessageFactory.getLabel(context, component), value != null ? value.toString() : null), "");
    }

    private static void primefacesSupport(UIComponent component, String val) {
	// PrimeFaces support
	if (component instanceof Calendar) {
	    Calendar cal = (Calendar) component;
	    cal.setValue(val);
	}
    }

    private DateTimeFormatter patternOrDefault(String pattern) {
	if (pattern != null && !pattern.trim().isEmpty())
	    try {
		return DateTimeFormatter.ofPattern(pattern);
	    } catch (IllegalArgumentException e) {
	    }
	return getDefaultFormatter();
    }

    protected abstract String getWich();

    protected abstract T parse(String value, DateTimeFormatter df);

    protected abstract String format(T value, DateTimeFormatter df);

    protected abstract DateTimeFormatter getDefaultFormatter();
}
