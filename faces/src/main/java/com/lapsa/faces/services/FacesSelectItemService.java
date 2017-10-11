package com.lapsa.faces.services;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import tech.lapsa.java.commons.localization.Localized;
import tech.lapsa.java.commons.localization.LocalizedService;

public interface FacesSelectItemService<T extends Localized> extends LocalizedService<T> {

    //

    default List<SelectItem> generateItemsSI(Supplier<T[]> listSupplier, Function<T, SelectItem> selectItemSupplier) {
	return Arrays.stream(listSupplier.get()).map(selectItemSupplier).collect(Collectors.toList());
    }

    //

    @Override
    default String displayName(T entity) {
	// TODO HACK подумать как развести CDI и JSF бины
	if (FacesContext.getCurrentInstance() == null)
	    return LocalizedService.super.displayName(entity);

	return entity.displayName(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    @Default
    default String displayNameShort(T entity) {
	// TODO HACK подумать как развести CDI и JSF бины
	if (FacesContext.getCurrentInstance() == null)
	    return LocalizedService.super.displayNameShort(entity);

	return entity.displayNameShort(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    @Default
    default String displayNameFull(T entity) {
	// TODO HACK подумать как развести CDI и JSF бины
	if (FacesContext.getCurrentInstance() == null)
	    return LocalizedService.super.displayNameFull(entity);

	return entity.displayNameFull(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    //

    default SelectItem selectItem(T entity) {
	return new SelectItem(entity, displayName(entity));
    }

    default SelectItem selectItemFull(T entity) {
	return new SelectItem(entity, displayNameFull(entity));
    }

    default SelectItem selectItemShort(T entity) {
	return new SelectItem(entity, displayNameShort(entity));
    }
}
