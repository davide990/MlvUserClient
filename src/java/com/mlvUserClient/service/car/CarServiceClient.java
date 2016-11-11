/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.service.car;

import com.mlv.client.service.carservice.Car;
import com.mlv.client.service.carservice.MlvCarsService;
import com.mlv.client.service.carservice.MlvCarsService_Service;
import com.mlv.client.service.carservice.Vehicle;
import com.mlvUserClient.binding.CarEntity;
import com.mlvUserClient.service.rental.RentalServiceClient;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class CarServiceClient {

    public static List<CarEntity> getAvaibleCars() {
        MlvCarsService_Service sv = new MlvCarsService_Service();
        MlvCarsService service = sv.getMlvCarsServicePort();

        List<CarEntity> cl = new ArrayList<>();
        for (Vehicle v : service.getAllVehicles()) {
            cl.add(CarEntity.fromCar((Car) v));
        }
        return cl;
    }

    public static List<CarEntity> getCarsOnSale() {
        MlvCarsService_Service sv = new MlvCarsService_Service();
        MlvCarsService service = sv.getMlvCarsServicePort();

        List<CarEntity> cl = new ArrayList<>();
        for (Vehicle v : service.getAllVehicles()) {
            if (isCarOnSale(v.getId()) && !RentalServiceClient.isCarRented(v.getId())) {
                cl.add(CarEntity.fromCar((Car) v));
            }
        }
        return cl;
    }

    public static boolean isCarOnSale(int id) {
        MlvCarsService_Service sv = new MlvCarsService_Service();
        MlvCarsService service = sv.getMlvCarsServicePort();

        return service.isVehicleOnSale(id);
    }

    public static CarEntity getCar(int id) {
        MlvCarsService_Service sv = new MlvCarsService_Service();
        MlvCarsService service = sv.getMlvCarsServicePort();

        return CarEntity.fromCar(service.getCarByID(id));
    }
}
