package com.lapsa.commons.elements;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Supplier;

public interface LocalizedElement {

    String name();

    String getBundleBaseName();

    //

    default String canonicalName() {
	return String.format("%1$s.%2$s", this.getClass().getName(), name());
    }

    //

    default String displayName(final Supplier<Locale> localeSupplier) {
	return displayName(localeSupplier.get());
    }

    default String displayNameFull(final Supplier<Locale> localeSupplier) {
	return displayNameFull(localeSupplier.get());
    }

    default String displayNameShort(final Supplier<Locale> localeSupplier) {
	return displayNameShort(localeSupplier.get());
    }

    //

    default String displayName() {
	return displayName(Locale::getDefault);
    }

    default String displayNameFull() {
	return displayNameFull(Locale::getDefault);
    }

    default String displayNameShort() {
	return displayNameShort(Locale::getDefault);
    }

    //

    default String displayName(final Locale locale) {
	return CachedResourceBundles
		.byLocale(getBundleBaseName(), Objects.requireNonNull(locale, "Locale must provided")).flatMap(v -> {
		    try {
			return Optional
				.ofNullable(v.getString(String.format("%1$s.%2$s", this.getClass().getName(), name())));
		    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
			return Optional.empty();
		    }
		}).orElseThrow(IllegalArgumentException::new);
    }

    default String displayNameFull(final Locale locale) {
	return CachedResourceBundles
		.byLocale(getBundleBaseName(), Objects.requireNonNull(locale, "Locale must provided")).flatMap(v -> {
		    try {
			return Optional
				.ofNullable(v
					.getString(String.format("%1$s.%2$s.full", this.getClass().getName(), name())));
		    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
			return Optional.ofNullable(displayName(locale));
		    }
		}).orElseThrow(IllegalArgumentException::new);
    }

    default String displayNameShort(final Locale locale) {
	return CachedResourceBundles
		.byLocale(getBundleBaseName(), Objects.requireNonNull(locale, "Locale must provided")).flatMap(v -> {
		    try {
			return Optional
				.ofNullable(v.getString(
					String.format("%1$s.%2$s.short", this.getClass().getName(), name())));
		    } catch (Exception e) {
			return Optional.ofNullable(displayName(locale));
		    }
		}).orElseThrow(IllegalArgumentException::new);
    }

    //

    static class CachedResourceBundles {

	private static final ConcurrentMap<String, ConcurrentMap<Locale, ResourceBundle>> CACHE = new ConcurrentHashMap<>();

	private static Optional<ResourceBundle> byLocale(final String baseName, final Locale locale) {
	    return Optional.ofNullable(
		    CACHE //
			    .computeIfAbsent(baseName, x -> new ConcurrentHashMap<>()) //
			    .computeIfAbsent(locale, x -> {
				try {
				    return ResourceBundle.getBundle(baseName, x);
				} catch (NullPointerException | MissingResourceException e) {
				    return null;
				}
			    }) //
	    );
	}
    }

}