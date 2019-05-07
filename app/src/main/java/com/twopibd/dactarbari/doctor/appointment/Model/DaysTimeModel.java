package com.twopibd.dactarbari.doctor.appointment.Model;

/**
 * Created by mukul on 4/22/2019.
 */

public class DaysTimeModel {
    boolean isSelected=false;
    String dayName;
    String startTime;
    String endTime;

    public DaysTimeModel() {
    }

    public DaysTimeModel(boolean isSelected, String dayName, String startTime, String endTime) {
        this.isSelected = isSelected;
        this.dayName = dayName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}