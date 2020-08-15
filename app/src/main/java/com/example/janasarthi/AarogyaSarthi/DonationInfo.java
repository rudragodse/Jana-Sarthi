package com.example.janasarthi.AarogyaSarthi;

public class DonationInfo {

    DonationInfo()
    {

    }

    public DonationInfo(String titleofOrg, String category, String desc, String contact) {
        this.titleofOrg = titleofOrg;
        Category = category;
        this.desc = desc;
        this.contact = contact;
    }

    public String getTitleofOrg() {
        return titleofOrg;
    }

    public void setTitleofOrg(String titleofOrg) {
        this.titleofOrg = titleofOrg;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    String titleofOrg;
    String Category;
    String desc;
    String contact;

}
