package com.lapsa.faces.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localDateTimeConverter", forClass = LocalDateTime.class)
public class LocalDateTimeConverter extends TemporalConverter<LocalDateTime> {

    private static final String WHICH = "Date/Time";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public LocalDateTimeConverter() {
	super(LocalDateTime.class);
    }

    @Override
    protected String getWich() {
	return WHICH;
    }

    @Override
    protected LocalDateTime parse(String value, DateTimeFormatter df) {
	return LocalDateTime.parse(value, df);
    }

    @Override
    protected String format(LocalDateTime value, DateTimeFormatter df) {
	return df.format(value);
    }

    @Override
    protected DateTimeFormatter getDefaultFormatter() {
	return DEFAULT_FORMATTER;
    }
}
