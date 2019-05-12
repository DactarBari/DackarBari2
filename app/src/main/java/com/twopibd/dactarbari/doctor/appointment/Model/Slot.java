package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Slot {

@SerializedName("time")
@Expose
private String time;
@SerializedName("isAvailable")
@Expose
private Boolean isAvailable;

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public Boolean getIsAvailable() {
return isAvailable;
}

public void setIsAvailable(Boolean isAvailable) {
this.isAvailable = isAvailable;
}

}