package com.example.janasarthi.DhanSarthi;

public class CovidEssentialProduct {

    String nameofproduct,link; int imageforproduct;

    public CovidEssentialProduct(String nameofproduct, String link, int imageforproduct) {
        this.nameofproduct = nameofproduct;
        this.link = link;
        this.imageforproduct = imageforproduct;
    }

    public String getNameofproduct() {
        return nameofproduct;
    }

    public void setNameofproduct(String nameofproduct) {
        this.nameofproduct = nameofproduct;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getImageforproduct() {
        return imageforproduct;
    }

    public void setImageforproduct(int imageforproduct) {
        this.imageforproduct = imageforproduct;
    }
}
