package com.lapsa.faces.services;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import javax.enterprise.inject.Default;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import com.lapsa.commons.elements.ListingService;
import com.lapsa.commons.elements.LocalizedElement;
import com.lapsa.commons.elements.LocalizedElementService;

public interface FacesSelectItemService<T extends LocalizedElement>
	extends LocalizedElementService<T>, ListingService<T> {

    @Override
    default String displayName(T entity) {
	// TODO HACK подумать как развести CDI и JSF бины
	if (FacesContext.getCurrentInstance() == null)
	    return LocalizedElementService.super.displayName(entity);

	return entity.displayName(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    @Default
    default String displayNameShort(T entity) {
	// TODO HACK подумать как развести CDI и JSF бины
	if (FacesContext.getCurrentInstance() == null)
	    return LocalizedElementService.super.displayNameShort(entity);

	return entity.displayNameShort(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    @Default
    default String displayNameFull(T entity) {
	// TODO HACK подумать как развести CDI и JSF бины
	if (FacesContext.getCurrentInstance() == null)
	    return LocalizedElementService.super.displayNameFull(entity);

	return entity.displayNameFull(FacesContext.getCurrentInstance().getViewRoot().getLocale());
    }

    //

    default List<SelectItem> getAllItemsSI() {
	return generateItemsSI(this::getAll, this::selectItem);
    }

    default List<SelectItem> getAllItemsShortSI() {
	return generateItemsSI(this::getAll, this::selectItemShort);
    }

    default List<SelectItem> getAllItemsFullSI() {
	return generateItemsSI(this::getAll, this::selectItemFull);
    }

    //

    default List<SelectItem> getSelectableItemsSI() {
	return generateItemsSI(this::getSelectable, this::selectItem);
    }

    default List<SelectItem> getSelectableItemsShortSI() {
	return generateItemsSI(this::getSelectable, this::selectItemShort);
    }

    default List<SelectItem> getSelectableItemsFullSI() {
	return generateItemsSI(this::getSelectable, this::selectItemFull);
    }

    //

    default List<SelectItem> generateItemsSI(Supplier<T[]> listSupplier, Function<T, SelectItem> selectItemSupplier) {
	return Arrays.stream(listSupplier.get()).map(selectItemSupplier).collect(Collectors.toList());
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

    //

    default List<SelectItem> getNonSelectableItemsSI() {
	return generateItemsSI(this::getNonSelectable, this::selectItem);
    }

    default List<SelectItem> getNonSelectableItemsShortSI() {
	return generateItemsSI(this::getNonSelectable, this::selectItemShort);
    }

    default List<SelectItem> getNonSelectableItemsFullSI() {
	return generateItemsSI(this::getNonSelectable, this::selectItemFull);
    }

}
