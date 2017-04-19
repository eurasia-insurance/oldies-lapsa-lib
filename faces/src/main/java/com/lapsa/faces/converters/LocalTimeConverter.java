package com.lapsa.faces.converters;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.faces.convert.FacesConverter;

@FacesConverter(value = "localTimeConverter", forClass = LocalTime.class)
public class LocalTimeConverter extends TemporalConverter<LocalTime> {

    private static final String WHICH = "Time";
    private static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    public LocalTimeConverter() {
	super(LocalTime.class);
    }

    @Override
    protected String getWich() {
	return WHICH;
    }

    @Override
    protected LocalTime parse(String value, DateTimeFormatter df) {
	return LocalTime.parse(value, df);
    }

    @Override
    protected String format(LocalTime value, DateTimeFormatter df) {
	return df.format(value);
    }

    @Override
    protected DateTimeFormatter getDefaultFormatter() {
	return DEFAULT_FORMATTER;
    }
}
