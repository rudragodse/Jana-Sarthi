package com.example.janasarthi.Users;

public class Doctor {
    String name,dateofregistration,email,specialization,state,password,registration_no;

    public Doctor(String name, String dateofregistration, String email, String specialization, String state, String password, String registration_no) {
        this.name = name;
        this.dateofregistration = dateofregistration;
        this.email = email;
        this.specialization = specialization;
        this.state = state;
        this.password = password;
        this.registration_no = registration_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateofregistration() {
        return dateofregistration;
    }

    public void setDateofregistration(String dateofregistration) {
        this.dateofregistration = dateofregistration;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public String getRegistration_no() {
        return registration_no;
    }

    public void setRegistration_no(String registration_no) {
        this.registration_no = registration_no;
    }
}
