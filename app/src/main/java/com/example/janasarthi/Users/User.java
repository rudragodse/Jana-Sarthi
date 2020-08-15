package com.example.janasarthi.Users;

public class User {
    String name,email,password,state,category;

    public User()
    {

    }

    public User(String name, String email, String password, String state, String category) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.state = state;
        this.category = category;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
