package test.validationMessages;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.validation.MessageInterpolator;
import javax.validation.Validation;

import org.hibernate.validator.internal.engine.MessageInterpolatorContext;
import org.junit.Before;
import org.junit.Test;

public class TestValidationMessages {

    private MessageInterpolator interpolator;

    @Before
    public void setup() {
	interpolator = Validation.byDefaultProvider().configure().getDefaultMessageInterpolator();
    }

    @Test
    public void testRussianBundle() {
	Locale locale = Locale.forLanguageTag(Constants.LANGUAGE_TAG_RUSSIAN);
	testBundle(locale, Constants.LANGUAGE_TAG_RUSSIAN);
    }

    @Test
    public void testKazakhBundle() {
	Locale locale = Locale.forLanguageTag(Constants.LANGUAGE_TAG_KAZAKH);
	testBundle(locale, Constants.LANGUAGE_TAG_KAZAKH);
    }

    @Test
    public void testEnglishBundle() {
	Locale locale = Locale.ENGLISH;
	testBundle(locale, Constants.LANGUAGE_TAG_ENGLISH);
    }

    private void testBundle(Locale locale, String expectingLanguageTag) {
	testLocale(locale, expectingLanguageTag);
	testResourceBundle(locale);
	testAnnotated(locale);
    }

    private void testLocale(Locale locale, String expectingLanguageTag) {
	assertThat(locale, not(nullValue()));
	assertThat(locale.getLanguage(), is(expectingLanguageTag));
    }

    private void testResourceBundle(Locale locale) {
	ResourceBundle resources = ResourceBundle.getBundle(Constants.VALIDATION_MESSAGES_BUNDLE_BASE_NAME, locale);
	assertThat(resources, not(nullValue()));
    }

    private void testAnnotated(Locale locale) {
	Method[] pseudoMethods = DummyAnnotated.class.getDeclaredMethods();
	for (Method pseudoMethod : pseudoMethods) {

	    Annotation[] annotations = pseudoMethod.getDeclaredAnnotations();
	    for (Annotation annotation : annotations) {

		Method m = null;
		try {
		    m = annotation.annotationType().getMethod("message");
		} catch (NoSuchMethodException e) {
		    fail(String.format("Annotation '%1$s' has no 'message()' method",
			    annotation.annotationType().getName()));
		}
		assertThat(m, not(nullValue()));

		Object messageKeyObject = m.getDefaultValue();
		assertThat(messageKeyObject, allOf(not(nullValue()), instanceOf(String.class)));
		String messageKey = (String) messageKeyObject;
		testMessageKey(locale, messageKey);
	    }
	}
    }

    private void testMessageKey(Locale locale, String messageKey) {
	assertThat("Message key must be non empty string", messageKey, allOf(not(nullValue()), not(isEmptyString())));
	String interpolatedMessage = null;
	try {
	    MessageInterpolatorContext context = new MessageInterpolatorContext(null, null, null);
	    interpolatedMessage = interpolator.interpolate(messageKey, context, locale);
	} catch (Exception e) {
	    fail(String.format("Error interpolating message key '%1$s' for locale '%2$s' : %3$s", messageKey,
		    locale.getDisplayName(Locale.ENGLISH), e.getMessage()));
	}
	assertThat("Interpolated message must be non-empty string", interpolatedMessage,
		allOf(not(nullValue()), not(isEmptyString())));
	System.out.println(String.format("key '%1$s' is interpolated as '%2$s' on locale '%3$s'", messageKey,
		interpolatedMessage, locale.getDisplayName(Locale.ENGLISH)));
    }

}
