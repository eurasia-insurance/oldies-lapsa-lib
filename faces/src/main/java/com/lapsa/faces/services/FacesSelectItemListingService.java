package com.lapsa.faces.services;

import java.util.List;

import javax.faces.model.SelectItem;

import com.lapsa.commons.elements.ListingService;
import com.lapsa.commons.elements.Localized;

public interface FacesSelectItemListingService<T extends Localized>
	extends FacesSelectItemService<T>, ListingService<T> {

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
