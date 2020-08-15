package com.example.janasarthi.Users;

public class Organization {
    String nameoforganization,service,contact,state,password,email;

    public Organization(String nameoforganization, String service, String contact, String state, String password, String email) {
        this.nameoforganization = nameoforganization;
        this.service = service;
        this.contact = contact;
        this.state = state;
        this.password = password;
        this.email = email;
    }

    public String getNameoforganization() {
        return nameoforganization;
    }

    public void setNameoforganization(String nameoforganization) {
        this.nameoforganization = nameoforganization;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
