package test;

import static org.junit.Assert.*;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.junit.Test;

public abstract class EnumTypeMessagesBundleTest<T extends Enum<?>> extends BaseMessagesBundleTest {

    @Test
    public void testRussianBundle() {
	testBundle(getBundleBaseName(), LANG_RU);
    }

    @Test
    public void testEnglishBundle() {
	testBundle(getBundleBaseName(), LANG_EN);
    }

    @Test
    public void testKazakhBundle() {
	testBundle(getBundleBaseName(), LANG_KK);
    }

    protected abstract T[] getAllEnumValues();

    protected abstract String getBundleBaseName();

    protected void testBundle(final String bundleBaseName, final String languageTag) {
	final Locale locale = getLocale(languageTag);
	final ResourceBundle resourceBundle = getResourceBundle(bundleBaseName, locale);
	for (final T c : getAllEnumValues()) {
	    final String key = String.format("%s.%s", c.getClass().getName(), c.name());
	    try {
		resourceBundle.getString(String.format("%s.%s", c.getClass().getName(), c.name()));
	    } catch (final MissingResourceException e) {
		fail(String.format("Missing key %1$s", key));
	    }
	}
    }
}
