package test;

import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import org.junit.Test;

import com.lapsa.utils.ElementsBundleBase;
import com.lapsa.utils.UtilsMessage;

public class MessagesBundleExceedTest extends BaseMessagesBundleTest {

    @Test
    public void testNoExcessRecordsRussian() {
	final Locale locale = getLocale(LANG_RU);
	final ResourceBundle resources = getResourceBundle(ElementsBundleBase.BUNDLE_BASENAME, locale);
	testBundle(resources);
    }

    @Test
    public void testNoExcessRecordsEnglish() {
	final Locale locale = getLocale(LANG_EN);
	final ResourceBundle resources = getResourceBundle(ElementsBundleBase.BUNDLE_BASENAME, locale);
	testBundle(resources);
    }

    @Test
    public void testNoExcessRecordsKazakh() {
	final Locale locale = getLocale(LANG_KK);
	final ResourceBundle resources = getResourceBundle(ElementsBundleBase.BUNDLE_BASENAME, locale);
	testBundle(resources);
    }

    private void testBundle(final ResourceBundle resources) {
	final Enumeration<String> keys = resources.getKeys();
	while (keys.hasMoreElements()) {
	    final String key = keys.nextElement();
	    if (findByFullName(UtilsMessage.values(), key) != null)
		continue;
	    fail(String.format("Resource bunddle key '%1$s' is outbinded", key));
	}
    }

    private <T extends Enum<?>> T findByFullName(final T[] values, final String key) {
	for (final T c : values) {
	    final String name = String.format("%s.%s", c.getClass().getName(), c.name());
	    if (name.equals(key))
		return c;
	    final String shrt = String.format("%s.%s.short", c.getClass().getName(), c.name());
	    if (shrt.equals(key))
		return c;
	}
	return null;
    }
}
