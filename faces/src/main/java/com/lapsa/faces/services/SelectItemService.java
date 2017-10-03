package com.lapsa.faces.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

import com.lapsa.commons.elements.Localized;

@Named("selectItemService")
@ApplicationScoped
public class SelectItemService implements FacesSelectItemService<Localized> {

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
