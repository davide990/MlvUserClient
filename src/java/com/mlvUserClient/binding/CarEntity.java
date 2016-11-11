/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.binding;

import com.mlv.client.service.carservice.Car;
import java.util.Objects;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class CarEntity extends Car {

    public static CarEntity fromCar(Car c) {
        CarEntity e = new CarEntity();
        e.setAirConditioner(c.isAirConditioner());
        e.setAutomaticTransmission(c.isAutomaticTransmission());
        e.setBrand(c.getBrand());
        e.setModel(c.getModel());
        e.setId(c.getId());
        e.setMaxSpeed(c.getMaxSpeed());
        e.setMaxPassengers(c.getMaxPassengers());
        e.setPurchaseDate(c.getPurchaseDate());
        e.setType(c.getType());
        e.setRentalPriceForDay(c.getRentalPriceForDay());
        e.setPrice(c.getPrice());
        e.setSold(c.isSold());
        return e;
    }

    public static Car toCar(CarEntity c) {
        Car e = new Car();
        e.setAirConditioner(c.isAirConditioner());
        e.setAutomaticTransmission(c.isAutomaticTransmission());
        e.setBrand(c.getBrand());
        e.setModel(c.getModel());
        e.setId(c.getId());
        e.setMaxSpeed(c.getMaxSpeed());
        e.setMaxPassengers(c.getMaxPassengers());
        e.setPurchaseDate(c.getPurchaseDate());
        e.setType(c.getType());
        e.setPrice(c.getPrice());
        e.setSold(c.isSold());
        return e;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + (this.airConditioner ? 1 : 0);
        hash = 17 * hash + (this.automaticTransmission ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.brand);
        hash = 17 * hash + Objects.hashCode(this.model);
        hash = 17 * hash + Objects.hashCode(this.purchaseDate);
        hash = 17 * hash + Objects.hashCode(this.type);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (this.airConditioner != other.isAirConditioner()) {
            return false;
        }
        if (this.automaticTransmission != other.isAutomaticTransmission()) {
            return false;
        }
        if (!Objects.equals(this.brand, other.getBrand())) {
            return false;
        }
        if (!Objects.equals(this.model, other.getModel())) {
            return false;
        }
        if (!Objects.equals(this.purchaseDate, other.getPurchaseDate())) {
            return false;
        }
        return this.type == other.getType();
    }

}
