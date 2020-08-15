package com.example.janasarthi.KaushalSarthi;

public class CourseInfo {

    public String sectorname,coursename,category,duration;

    public String getSectorname() {
        return sectorname;
    }

    public void setSectorname(String sectorname) {
        this.sectorname = sectorname;
    }

    public CourseInfo(String sectorname, String coursename, String category, String duration) {
        this.sectorname = sectorname;
        this.coursename = coursename;
        this.category = category;
        this.duration = duration;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
