package com.lapsa.faces.beans;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.FacesEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

@Named("lapsaVoid")
@Dependent
public class Void implements Serializable {
    private static final long serialVersionUID = -1667664768981313908L;

    public String doVoid() {
	return null;
    }

    public void onVoid() {
    }

    public void onVoidFacesEvent(FacesEvent event) {
    }

    public void onVoidValueChangeEvent(ValueChangeEvent event) {
    }

    public void onVoidAjaxBehaviorEvent(AjaxBehaviorEvent event) {
    }
}
