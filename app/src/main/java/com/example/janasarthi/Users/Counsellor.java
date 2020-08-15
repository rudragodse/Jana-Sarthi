package com.example.janasarthi.Users;

public class Counsellor {
    String name,email,specialization,qualification,password,state,contact;

    public Counsellor()
    {}




    public Counsellor(String name, String email, String specialization, String qualification, String password, String state, String contact) {
        this.name = name;
        this.email = email;
        this.specialization = specialization;
        this.qualification = qualification;
        this.password = password;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getQualification()
    {
        return qualification;
    }
    public void setQualification(String qualification)
    {
        this.qualification=qualification;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState()
    {
        return state;
    }
    public void setState(String state)
    {
        this.state = state;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
