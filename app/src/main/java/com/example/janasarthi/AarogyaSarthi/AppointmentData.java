package com.example.janasarthi.AarogyaSarthi;

public class AppointmentData {

    String currentuser,doctorname,dateofappointment,timeslot;



    public AppointmentData()
    {

    }

    public AppointmentData(String currentuser, String doctorname, String dateofappointment,String timeslot) {
        this.currentuser = currentuser;
        this.doctorname = doctorname;
        this.dateofappointment = dateofappointment;
        this.timeslot = timeslot;

    }

    public String getCurrentuser() {
        return currentuser;
    }

    public void setCurrentuser(String currentuser) {
        this.currentuser = currentuser;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getDateofappointment() {
        return dateofappointment;
    }

    public void setDateofappointment(String dateofappointment) {
        this.dateofappointment = dateofappointment;
    }

    public String getTimeslot() {
        return timeslot;
    }

    public void setTimeslot(String timeslot) {
        this.timeslot = timeslot;
    }
}
