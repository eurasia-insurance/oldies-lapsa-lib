package com.lapsa.faces.converters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateConverter", forClass = LocalDate.class)
public class LocalDateConverter extends TemporalConverter<LocalDate> {
    private static final String WHICH = "Date";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;

    public LocalDateConverter() {
	super(LocalDate.class);
    }

    @Override
    protected String getWich() {
	return WHICH;
    }

    @Override
    protected LocalDate parse(String value, DateTimeFormatter df) {
	return LocalDate.parse(value, df);
    }

    @Override
    protected String format(LocalDate value, DateTimeFormatter df) {
	return df.format(value);
    }

    @Override
    protected DateTimeFormatter getDefaultFormatter() {
	return DEFAULT_FORMATTER;
    }
}
