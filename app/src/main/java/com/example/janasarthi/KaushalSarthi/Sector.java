package com.example.janasarthi.KaushalSarthi;

public class Sector {

    public String sectorname,coursename,duration,tradetype;

    public Sector()
    {

    }

    public Sector(String sectorname, String coursename, String duration, String tradetype) {
        this.sectorname = sectorname;
        this.coursename = coursename;
        this.duration = duration;
        this.tradetype = tradetype;
    }

    public String getSectorname() {
        return sectorname;
    }

    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }
}
