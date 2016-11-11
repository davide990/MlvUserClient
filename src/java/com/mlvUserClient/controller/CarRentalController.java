/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.controller;

import com.mlv.client.service.rentalservice.Car;
import com.mlv.client.service.rentalservice.CarType;
import com.mlv.client.service.rentalservice.Rental;
import com.mlvUserClient.binding.CarEntity;
import com.mlvUserClient.login;
import com.mlvUserClient.service.car.CarServiceClient;
import com.mlvUserClient.service.rental.RentalServiceClient;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Named("carRentalController")
@SessionScoped
public class CarRentalController implements Serializable {

    private List<CarEntity> avaibleCars;
    private Rental current;
    private CarEntity car;

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }

    public String prepareCreateRental() {
        current = new Rental();
        avaibleCars = CarServiceClient.getAvaibleCars();
        return "CarRental";
    }

    public String prepareToCreateRental() {
        current.setRentalPrice(getTotalRentalPrice());
        current.setClientId(login.getLoggedPerson().getId());
        
        Car r = new Car();
        r.setType(CarType.fromValue(car.getType().toString()));
        r.setAirConditioner(car.isAirConditioner());
        r.setAutomaticTransmission(car.isAutomaticTransmission());
        r.setBrand(car.getBrand());
        r.setModel(car.getModel());
        r.setId(car.getId());
        r.setMaxPassengers(car.getMaxPassengers());
        r.setMaxSpeed(car.getMaxSpeed());
        r.setPurchaseDate(car.getPurchaseDate());
        r.setRentalPriceForDay(car.getRentalPriceForDay());
        
        current.setCar(r);

        return "ConfirmPurchaseRental";
    }

    /*
     * Converts XMLGregorianCalendar to java.util.Date in Java
     */
    private Date toDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
        GregorianCalendar gCalendar = new GregorianCalendar();
        gCalendar.setTime(date);
        XMLGregorianCalendar xmlCalendar = null;
        try {
            xmlCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendar(gCalendar);
        } catch (DatatypeConfigurationException ex) {
            Logger.getLogger(CarRentalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return xmlCalendar;
    }

    public int getTotalRentalPrice() {
        Date start = toDate(current.getRentalStart());
        Date end = toDate(current.getRentalEnd());
        return (int) getDifferenceDays(start, end) * car.getRentalPriceForDay();
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public String create() {
        try {
            if (!RentalServiceClient.isRentalValid(login.getLoggedPerson(), current)) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Rental request is not valid.");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return prepareCreateRental();
            }
            RentalServiceClient.Add(login.getLoggedPerson(), current);

            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Rental request sent");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Rental request cannot be sent");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        return prepareCreateRental();
    }

    public List<CarEntity> getAvaibleCars() {
        return avaibleCars;
    }

    public void setAvaibleCars(List<CarEntity> avaibleCars) {
        this.avaibleCars = avaibleCars;
    }

    public Rental getCurrent() {
        return current;
    }

    public void setCurrent(Rental current) {
        this.current = current;
    }

    public Date getRentalStart() {
        return toDate(current.getRentalStart());
    }

    public void setRentalStart(Date d) {
        current.setRentalStart(toXMLGregorianCalendar(d));
    }

    public Date getRentalEnd() {
        return toDate(current.getRentalEnd());
    }

    public void setRentalEnd(Date d) {
        current.setRentalEnd(toXMLGregorianCalendar(d));
    }

}
