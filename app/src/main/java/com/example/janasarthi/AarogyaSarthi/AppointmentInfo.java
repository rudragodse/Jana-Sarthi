package com.example.janasarthi.AarogyaSarthi;

public class AppointmentInfo {

    String timing,bookedunderthename;

    public AppointmentInfo(String timing, String bookedunderthename) {
        this.timing = timing;
        this.bookedunderthename = bookedunderthename;

    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }

    public String getBookedunderthename() {
        return bookedunderthename;
    }

    public void setBookedunderthename(String bookedunderthename) {
        this.bookedunderthename = bookedunderthename;
    }


}
