package com.twopibd.dactarbari.doctor.appointment.Model;

public class FeatureType {
    String name,action,type;
    int photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public FeatureType(String name, String action, String type, int photo) {
        this.name = name;
        this.action = action;
        this.type = type;
        this.photo = photo;
    }
}
