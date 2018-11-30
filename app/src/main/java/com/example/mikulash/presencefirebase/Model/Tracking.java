package com.example.mikulash.presencefirebase.Model;

public class Tracking {
    private String email, uid, latitude, longitude;

    public Tracking() {

    }

    public Tracking(String email, String uid, String latitude, String longitude) {
        this.email = email;
        this.uid = uid;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
