package com.twopibd.dactarbari.doctor.appointment.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChamberInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("doctor_id")
@Expose
private Integer doctorId;
@SerializedName("chamber_address")
@Expose
private String chamberAddress;
@SerializedName("chamber_latitude")
@Expose
private String chamberLatitude;
@SerializedName("chamber_longitude")
@Expose
private String chamberLongitude;
@SerializedName("fee")
@Expose
private Integer fee;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("schedule_info")
@Expose
private List<ScheduleInfo> scheduleInfo = null;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getDoctorId() {
return doctorId;
}

public void setDoctorId(Integer doctorId) {
this.doctorId = doctorId;
}

public String getChamberAddress() {
return chamberAddress;
}

public void setChamberAddress(String chamberAddress) {
this.chamberAddress = chamberAddress;
}

public String getChamberLatitude() {
return chamberLatitude;
}

public void setChamberLatitude(String chamberLatitude) {
this.chamberLatitude = chamberLatitude;
}

public String getChamberLongitude() {
return chamberLongitude;
}

public void setChamberLongitude(String chamberLongitude) {
this.chamberLongitude = chamberLongitude;
}

public Integer getFee() {
return fee;
}

public void setFee(Integer fee) {
this.fee = fee;
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

public List<ScheduleInfo> getScheduleInfo() {
return scheduleInfo;
}

public void setScheduleInfo(List<ScheduleInfo> scheduleInfo) {
this.scheduleInfo = scheduleInfo;
}

}