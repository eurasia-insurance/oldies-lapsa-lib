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

    default String displayName() {
	return displayName(Locale.getDefault());
    }

    default String displayNameFull() {
	return displayNameFull(Locale.getDefault());
    }

    default String displayNameShort() {
	return displayNameShort(Locale.getDefault());
    }

    //

    default String displayName(Supplier<ResourceBundle> bundleSupplier) {
	return Optional.ofNullable(bundleSupplier.get()).flatMap(v -> {
	    try {
		return Optional
			.ofNullable(v.getString(String.format("%1$s.%2$s", this.getClass().getName(), name())));
	    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
		return Optional.empty();
	    }
	}).orElseThrow(() -> new IllegalArgumentException("No ResourceBundle is supplied"));
    }

    default String displayNameFull(Supplier<ResourceBundle> bundleSupplier) {
	return Optional.ofNullable(bundleSupplier.get()).flatMap(v -> {
	    try {
		return Optional
			.ofNullable(v.getString(String.format("%1$s.%2$s.full", this.getClass().getName(), name())));
	    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
		return Optional.ofNullable(displayName(bundleSupplier));
	    }
	}).orElseThrow(() -> new IllegalArgumentException("No ResourceBundle is supplied"));
    }

    default String displayNameShort(Supplier<ResourceBundle> bundleSupplier) {
	return Optional.ofNullable(bundleSupplier.get()).flatMap(v -> {
	    try {
		return Optional
			.ofNullable(v.getString(String.format("%1$s.%2$s.short", this.getClass().getName(), name())));
	    } catch (NullPointerException | MissingResourceException | ClassCastException e) {
		return Optional.ofNullable(displayName(bundleSupplier));
	    }
	}).orElseThrow(() -> new IllegalArgumentException("No ResourceBundle is supplied"));
    }

    //

    default String displayName(final Locale locale) {
	return displayName(() -> CachedResourceBundles.getCached(getBundleBaseName(), locale));
    }

    default String displayNameFull(final Locale locale) {
	return displayNameFull(() -> CachedResourceBundles.getCached(getBundleBaseName(), locale));
    }

    default String displayNameShort(final Locale locale) {
	return displayNameShort(() -> CachedResourceBundles.getCached(getBundleBaseName(), locale));
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