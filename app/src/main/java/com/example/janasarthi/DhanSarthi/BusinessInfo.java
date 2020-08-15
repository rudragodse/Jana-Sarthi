package com.example.janasarthi.DhanSarthi;

import java.io.Serializable;

public class BusinessInfo implements Serializable {

    String business_sector,business_stage,business_state;

    public BusinessInfo(String business_sector, String business_stage, String business_state) {
        this.business_sector = business_sector;
        this.business_stage = business_stage;
        this.business_state = business_state;
    }

    public String getBusiness_sector() {
        return business_sector;
    }

    public void setBusiness_sector(String business_sector) {
        this.business_sector = business_sector;
    }

    public String getBusiness_stage() {
        return business_stage;
    }

    public void setBusiness_stage(String business_stage) {
        this.business_stage = business_stage;
    }

    public String getBusiness_state() {
        return business_state;
    }

    public void setBusiness_state(String business_state) {
        this.business_state = business_state;
    }
}
