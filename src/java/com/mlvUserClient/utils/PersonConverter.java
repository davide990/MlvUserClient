package com.mlvUserClient.utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.mlvUserClient.binding.PersonEntity;
import com.mlvUserClient.service.user.UserServiceClient;
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
@FacesConverter("personConverter")
public class PersonConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
//
//        FacesContext facesContext = FacesContext.getCurrentInstance();
//        RentalController neededBean
//                = (RentalController) facesContext.getApplication()
//                .getELResolver().getValue(context.getELContext(), null, "rentalController");

        PersonEntity result = UserServiceClient.retrieveMLVUserByID(Integer.parseInt(value));

        return result;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (!(value instanceof PersonEntity)) {
            return "";
        }
        PersonEntity s = (PersonEntity) value;
        String l = Long.toString(s.getId());
        return l;
    }
    
}
