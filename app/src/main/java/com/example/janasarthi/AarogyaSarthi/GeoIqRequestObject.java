package com.example.janasarthi.AarogyaSarthi;

public class GeoIqRequestObject {

    String key;
    double lng,lat;int radius;

    public GeoIqRequestObject(String key, double lng, double lat, int radius) {
        this.key = key;
        this.lng = lng;
        this.lat = lat;
        this.radius = radius;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public double getLng() {
        return lng;
    }

    public double getLat() {
        return lat;
    }

    public int getRadius() {
        return radius;
    }
}
