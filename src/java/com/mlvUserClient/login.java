/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient;

import com.mlvUserClient.binding.PersonEntity;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
public class login {

    public static PersonEntity loggedPerson;

    public static PersonEntity getLoggedPerson() {
        return loggedPerson;
    }

    public static void setLoggedPerson(PersonEntity loggedPerson) {
        login.loggedPerson = loggedPerson;
    }

}
