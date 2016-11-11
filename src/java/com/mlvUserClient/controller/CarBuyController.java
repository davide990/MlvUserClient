/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.controller;

import com.mlvUserClient.binding.CarEntity;
import com.mlvUserClient.login;
import com.mlvUserClient.service.car.CarServiceClient;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Named("carBuyController")
@SessionScoped
public class CarBuyController implements Serializable {

    private List<CarEntity> avaibleCars;
    private CarEntity selected;

    public List<CarEntity> getAvaibleCars() {
        return avaibleCars;
    }

    public void setAvaibleCars(List<CarEntity> avaibleCars) {
        this.avaibleCars = avaibleCars;
    }

    public CarEntity getSelected() {
        return selected;
    }

    public void setSelected(CarEntity selected) {
        this.selected = selected;
    }

    public String prepareCarBuy() {
        avaibleCars = CarServiceClient.getCarsOnSale();
        return "CarBuy";
    }

    public String buySelectedCar() {

        boolean userCanBuy = CarServiceClient.validateCarPurchase(login.getLoggedPerson().getId(), selected.getId());

        if (userCanBuy) {
            CarServiceClient.buyCar(login.getLoggedPerson().getId(), selected.getId());
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Car bought!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Car cannot be bought!");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return prepareCarBuy();
    }

    public String prepareToBuySelected() {

        return "ConfirmPurchaseCar";
    }

}
