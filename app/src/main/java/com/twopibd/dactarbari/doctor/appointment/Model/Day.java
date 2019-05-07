package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Day {
    String day,start_time,end_time,patient_capacity;

    public Day() {
    }

    public Day(String day, String start_time, String end_time, String patient_capacity) {
        this.day = day;
        this.start_time = start_time;
        this.end_time = end_time;
        this.patient_capacity = patient_capacity;
    }

    public String getPatient_capacity() {
        return patient_capacity;
    }

    public void setPatient_capacity(String patient_capacity) {
        this.patient_capacity = patient_capacity;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}