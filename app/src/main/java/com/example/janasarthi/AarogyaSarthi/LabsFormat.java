package com.example.janasarthi.AarogyaSarthi;

public class LabsFormat {
    private String nameoforganization;
    private String city;
    private String phonenumber;
    private String category;

    LabsFormat()
    {}




    public LabsFormat(String nameoforganization, String city, String phonenumber, String category) {
        this.nameoforganization = nameoforganization;
        this.city = city;
        this.phonenumber = phonenumber;
        this.category = category;
    }

    public String getNameoforganization() {
        return nameoforganization;
    }

    public void setNameoforganization(String nameoforganization) {
        this.nameoforganization = nameoforganization;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
