package com.example.janasarthi.DhanSarthi;

public class SubServicesInfo {

    String nameofservice,sub_service_name,link;

    public SubServicesInfo(String nameofservice,String sub_service_name, String link) {
        this.nameofservice = nameofservice;
        this.sub_service_name = sub_service_name;
        this.link = link;
    }

    public String getNameofservice() {
        return nameofservice;
    }

    public void setNameofservice(String nameofservice) {
        this.nameofservice = nameofservice;
    }

    public String getSub_service_name() {
        return sub_service_name;
    }

    public void setSub_service_name(String sub_service_name) {
        this.sub_service_name = sub_service_name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
