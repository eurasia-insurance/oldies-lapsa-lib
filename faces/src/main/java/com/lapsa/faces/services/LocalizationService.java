package com.lapsa.faces.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import tech.lapsa.java.commons.localization.Localized;

@Named("localizationService")
@ApplicationScoped
public class LocalizationService implements FacesSelectItemService<Localized> {

    public List<SelectItem> toSelectItems(final Localized[] list) {
	return generateItemsSI(() -> list, this::selectItem);
    }

    public List<SelectItem> toSelectItemsShort(final Localized[] list) {
	return generateItemsSI(() -> list, this::selectItemShort);
    }

    public List<SelectItem> toSelectItemsFull(final Localized[] list) {
	return generateItemsSI(() -> list, this::selectItemShort);
    }
}
