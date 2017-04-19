package test.validationMessages;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

public class ValidationMessagesBundleTest {

    @Test
    public void testNoExcessRecordsRussian() {
	Locale locale = Locale.forLanguageTag(Constants.LANGUAGE_TAG_RUSSIAN);
	assertThat(locale.getLanguage(), allOf(not(nullValue()), is(Constants.LANGUAGE_TAG_RUSSIAN)));
	ResourceBundle resources = ResourceBundle.getBundle(Constants.VALIDATION_MESSAGES_BUNDLE_BASE_NAME, locale);
	assertThat(resources, not(nullValue()));
	testBundle(resources);
    }

    @Test
    public void testNoExcessRecordsKazakh() {
	Locale locale = Locale.forLanguageTag(Constants.LANGUAGE_TAG_KAZAKH);
	assertThat(locale.getLanguage(), allOf(not(nullValue()), is(Constants.LANGUAGE_TAG_KAZAKH)));
	ResourceBundle resources = ResourceBundle.getBundle(Constants.VALIDATION_MESSAGES_BUNDLE_BASE_NAME, locale);
	assertThat(resources, not(nullValue()));
	testBundle(resources);
    }

    @Test
    public void testNoExcessRecordsEnglish() {
	Locale locale = Locale.ENGLISH;
	assertThat(locale.getLanguage(), allOf(not(nullValue()), is(Constants.LANGUAGE_TAG_ENGLISH)));
	ResourceBundle resources = ResourceBundle.getBundle(Constants.VALIDATION_MESSAGES_BUNDLE_BASE_NAME, locale);
	assertThat(resources, not(nullValue()));
	testBundle(resources);
    }

    private void testBundle(ResourceBundle resources) {
	Enumeration<String> keys = resources.getKeys();
	while (keys.hasMoreElements()) {
	    String key = keys.nextElement();
	    assertThat(String.format("Validation messages key must ends with '%1$s'", Constants.KEY_SUFFIX), key,
		    endsWith(Constants.KEY_SUFFIX));
	    String className = key.substring(0, key.length() - Constants.KEY_SUFFIX.length());
	    Class<?> cls = null;
	    try {
		cls = Class.forName(className);
	    } catch (ClassNotFoundException e) {
		fail(String.format("Not found class for name '%1$s' of resource bundle", className));
	    }
	    if (!cls.isAnnotation())
		fail(String.format("Class '%1$s' is not annotation", cls.getName()));
	}
    }
}
