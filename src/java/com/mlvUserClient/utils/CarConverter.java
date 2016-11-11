/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.utils;

import com.mlvUserClient.binding.CarEntity;
import com.mlvUserClient.service.car.CarServiceClient;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Named
@FacesConverter("carConverter")
public class CarConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String id) {
        return CarServiceClient.getCar(Integer.parseInt(id));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return Integer.toString(((CarEntity) value).getId());
    }

}