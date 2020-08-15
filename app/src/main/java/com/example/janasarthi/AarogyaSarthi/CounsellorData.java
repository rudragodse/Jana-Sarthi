package com.example.janasarthi.AarogyaSarthi;

public class CounsellorData {

    String name,qualification,specialization;

    public CounsellorData(String name, String qualification, String specialization) {
        this.name = name;
        this.qualification = qualification;
        this.specialization = specialization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
