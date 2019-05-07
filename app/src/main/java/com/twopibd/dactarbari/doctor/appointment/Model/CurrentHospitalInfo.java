package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentHospitalInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("doctor_id")
@Expose
private Integer doctorId;
@SerializedName("hospital_id")
@Expose
private Integer hospitalId;
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

public Integer getDoctorId() {
return doctorId;
}

public void setDoctorId(Integer doctorId) {
this.doctorId = doctorId;
}

public Integer getHospitalId() {
return hospitalId;
}

public void setHospitalId(Integer hospitalId) {
this.hospitalId = hospitalId;
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