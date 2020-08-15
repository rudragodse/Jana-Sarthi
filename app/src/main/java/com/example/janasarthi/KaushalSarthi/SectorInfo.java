package com.example.janasarthi.KaushalSarthi;

public class SectorInfo {

    public String sectoname;
    public int thumbnail;

    public SectorInfo(String sectoname, int thumbnail) {
        this.sectoname = sectoname;
        this.thumbnail = thumbnail;
    }

    public String getSectoname() {
        return sectoname;
    }

    public void setSectoname(String sectoname) {
        this.sectoname = sectoname;
    }

    public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }
}
