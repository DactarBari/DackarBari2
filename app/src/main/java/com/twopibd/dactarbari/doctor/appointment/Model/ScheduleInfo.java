package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScheduleInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("chamber_id")
@Expose
private Integer chamberId;
@SerializedName("day")
@Expose
private Integer day;
@SerializedName("start_time")
@Expose
private String startTime;
@SerializedName("end_time")
@Expose
private String endTime;
@SerializedName("patient_capacity")
@Expose
private Integer patientCapacity;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getChamberId() {
return chamberId;
}

public void setChamberId(Integer chamberId) {
this.chamberId = chamberId;
}

public Integer getDay() {
return day;
}

public void setDay(Integer day) {
this.day = day;
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

public Integer getPatientCapacity() {
return patientCapacity;
}

public void setPatientCapacity(Integer patientCapacity) {
this.patientCapacity = patientCapacity;
}

public String getCreatedAt() {
return createdAt;
}

public void setCreatedAt(String createdAt) {
this.createdAt = createdAt;
}

public String getUpdatedAt() {
return updatedAt;
}

public void setUpdatedAt(String updatedAt) {
this.updatedAt = updatedAt;
}

}