package test;

import com.lapsa.utils.ElementsBundleBase;
import com.lapsa.utils.UtilsMessage;

public class UtilsMessageBundleTest extends EnumTypeMessagesBundleTest<UtilsMessage> {

    @Override
    protected UtilsMessage[] getAllEnumValues() {
	return UtilsMessage.values();
    }

    @Override
    protected String getBundleBaseName() {
	return ElementsBundleBase.BUNDLE_BASENAME;
    }
}
