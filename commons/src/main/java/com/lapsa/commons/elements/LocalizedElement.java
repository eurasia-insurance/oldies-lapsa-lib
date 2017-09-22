package com.lapsa.commons.elements;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;

import com.lapsa.commons.function.Predicates;

public interface LocalizedElement {

    public static enum DisplayNameVariant {
	NORMAL("%1$s.%2$s"), FULL("%1$s.%2$s.full"), SHORT("%1$s.%2$s.short");

	private final Function<LocalizedElement, String> BUNDLE_KEY_RESOLVER;

	private DisplayNameVariant(String keyPatern) {
	    this.BUNDLE_KEY_RESOLVER = x -> String.format(keyPatern, x.getClass().getName(), x.name());
	}
    }

    //

    String name();

    String getBundleBaseName();

    //

    default String canonicalName() {
	return String.format("%1$s.%2$s", this.getClass().getName(), name());
    }

    //

    default String displayName(DisplayNameVariant variant) {
	return displayName(variant, Locale.getDefault());
    }

    default String displayName(DisplayNameVariant variant, final Locale locale) {
	ResourceBundle b = CachedResourceBundles.getCached(getBundleBaseName(), locale);
	return displayName(variant, b);
    }

    default String displayName(DisplayNameVariant variant, ResourceBundle bundle) {

	Builder<DisplayNameVariant> builder = Stream.<DisplayNameVariant> builder() //
		.add(variant);
	switch (variant) {
	case FULL:
	case SHORT:
	    builder.add(DisplayNameVariant.NORMAL);
	    break;
	default:
	}
	
	return builder.build() //
		.map(x -> {
		    try {
			return bundle.getString(x.BUNDLE_KEY_RESOLVER.apply(this));
		    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
			return null;
		    }
		}) //
		.filter(Predicates.objectNotNull()) //
		.findFirst()
		.orElseThrow(() -> new IllegalArgumentException("No ResourceBundle is supplied or key "
			+ "is not mapped correctly"));
    }

    //

    default String displayName() {
	return displayName(DisplayNameVariant.NORMAL, Locale.getDefault());
    }

    default String displayNameFull() {
	return displayName(DisplayNameVariant.FULL, Locale.getDefault());
    }

    default String displayNameShort() {
	return displayName(DisplayNameVariant.SHORT, Locale.getDefault());
    }

    //

    default String displayName(final Locale locale) {
	return displayName(DisplayNameVariant.NORMAL, locale);
    }

    default String displayNameFull(final Locale locale) {
	return displayName(DisplayNameVariant.FULL, locale);
    }

    default String displayNameShort(final Locale locale) {
	return displayName(DisplayNameVariant.SHORT, locale);
    }

    //

    default String displayName(ResourceBundle bundle) {
	return displayName(DisplayNameVariant.NORMAL, bundle); //
    }

    default String displayNameFull(ResourceBundle bundle) {
	return displayName(DisplayNameVariant.FULL, bundle); //
    }

    default String displayNameShort(ResourceBundle bundle) {
	return displayName(DisplayNameVariant.SHORT, bundle); //
    }

    //

    static class CachedResourceBundles {

	private static final ConcurrentMap<String, ConcurrentMap<Locale, ResourceBundle>> CACHE = new ConcurrentHashMap<>();

	private static ResourceBundle getCached(final String baseName, final Locale locale) {
	    return CACHE //
		    .computeIfAbsent(Objects.requireNonNull(baseName, "Basename must be provided"),
			    x -> new ConcurrentHashMap<>()) //
		    .computeIfAbsent(Objects.requireNonNull(locale, "Locale must be provided"), x -> {
			try {
			    return ResourceBundle.getBundle(baseName, x);
			} catch (NullPointerException | MissingResourceException e) {
			    return null;
			}
		    }) //
	    ;
	}
    }

}