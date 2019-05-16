package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slot {

@SerializedName("time")
@Expose
private String time;
@SerializedName("schedule_id")
@Expose
private String schedule_id;


public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}


    public String getSchedule_id() {
        return schedule_id;
    }

    public void setSchedule_id(String schedule_id) {
        this.schedule_id = schedule_id;
    }
}