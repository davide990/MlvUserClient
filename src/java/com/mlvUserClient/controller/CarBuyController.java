/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.controller;

import com.mlv.client.service.rentalservice.Rental;
import com.mlvUserClient.binding.CarEntity;
import com.mlvUserClient.service.car.CarServiceClient;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
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

}
