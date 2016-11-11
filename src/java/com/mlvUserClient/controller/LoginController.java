/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mlvUserClient.controller;

import com.mlvUserClient.binding.PersonEntity;
import com.mlvUserClient.login;
import com.mlvUserClient.service.user.UserServiceClient;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Davide Andrea Guastella <davide.guastella90@gmail.com>
 */
@Named("loginController")
@SessionScoped
public class LoginController implements Serializable {

    private List<PersonEntity> avaibleUsers;
    private PersonEntity selectedUser;

    public String prepareLogin() {
        avaibleUsers = UserServiceClient.retrieveMLVUsers();
        return "Login";
    }

    public String doLogin() {
        setLoggedPerson(selectedUser);

        return "UserMainPage";
    }

    public String doLogOut() {
        setLoggedPerson(null);
        return "Login";
    }

    public PersonEntity getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(PersonEntity selectedUser) {
        this.selectedUser = selectedUser;
    }

    public void setAvaibleUsers(List<PersonEntity> avaibleUsers) {
        this.avaibleUsers = avaibleUsers;
    }

    public String getUserType(PersonEntity e) {
        return UserServiceClient.getUserType(e);
    }

    public List<PersonEntity> getAvaibleUsers() {
        return avaibleUsers;
    }

    public PersonEntity getLoggedPerson() {
        return login.getLoggedPerson();
    }

    public void setLoggedPerson(PersonEntity loggedPerson) {
        login.setLoggedPerson(loggedPerson);
    }
}
