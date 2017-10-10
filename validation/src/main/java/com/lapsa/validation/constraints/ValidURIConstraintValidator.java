package com.lapsa.validation.constraints;

import java.net.URI;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lapsa.validation.ValidURI;

public class ValidURIConstraintValidator implements ConstraintValidator<ValidURI, URI> {

    private boolean mustAbsolute;
    private String[] allowedSchemes;

    @Override
    public void initialize(ValidURI constraintAnnotation) {
	mustAbsolute = constraintAnnotation.mustAbsolute();
	allowedSchemes = constraintAnnotation.allowedSchemes();
    }

    @Override
    public boolean isValid(URI value, ConstraintValidatorContext context) {
	if (value == null)
	    return true;
	if (mustAbsolute && !value.isAbsolute())
	    return false;
	if (allowedSchemes != null && allowedSchemes.length > 0
		&& Stream.of(allowedSchemes).noneMatch(x -> x.equals(value.getScheme())))
	    return false;
	return true;
    }
}