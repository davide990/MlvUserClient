/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.service.rental;

import com.mlv.client.service.rentalservice.MlvRentalService;
import com.mlv.client.service.rentalservice.MlvRentalService_Service;
import com.mlv.client.service.rentalservice.PersonEntity;
import com.mlv.client.service.rentalservice.Rental;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class RentalServiceClient {

    public static void Add(com.mlvUserClient.binding.PersonEntity user, Rental rental) {
        PersonEntity the_user = new PersonEntity();

        the_user.setBirthDate(user.getBirthDate());
        the_user.setEmail(user.getEmail());
        the_user.setFirstName(user.getFirstName());
        the_user.setLastName(user.getLastName());
        the_user.setIban(user.getIban());
        the_user.setId(user.getId());

        MlvRentalService_Service sv = new MlvRentalService_Service();
        MlvRentalService service = sv.getMlvRentalServicePort();

        service.addRental(the_user, rental);
    }

    public static boolean isRentalValid(com.mlvUserClient.binding.PersonEntity user, Rental rental) {
        PersonEntity the_user = new PersonEntity();

        the_user.setBirthDate(user.getBirthDate());
        the_user.setEmail(user.getEmail());
        the_user.setFirstName(user.getFirstName());
        the_user.setLastName(user.getLastName());
        the_user.setIban(user.getIban());
        the_user.setId(user.getId());

        MlvRentalService_Service sv = new MlvRentalService_Service();
        MlvRentalService service = sv.getMlvRentalServicePort();
        return service.validateRental(the_user, rental);
    }

}
