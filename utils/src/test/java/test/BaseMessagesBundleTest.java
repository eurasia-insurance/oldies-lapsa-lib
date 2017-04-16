package test;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseMessagesBundleTest {

    public static final String LANG_RU = "ru";
    public static final String LANG_EN = "en";
    public static final String LANG_KK = "kk";

    protected Locale getLocale(String languageTag) {
	Locale locale = Locale.forLanguageTag(languageTag);
	assertThat(locale, not(nullValue()));
	assertThat(locale.getLanguage(), allOf(not(nullValue()), is(languageTag)));
	return locale;
    }

    protected ResourceBundle getResourceBundle(String bundleBaseName, Locale locale) {
	ResourceBundle resourceBundle = ResourceBundle.getBundle(bundleBaseName, locale);
	assertThat(resourceBundle, not(nullValue()));
	return resourceBundle;
    }
}
