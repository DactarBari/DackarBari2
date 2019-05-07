package com.twopibd.dactarbari.doctor.appointment.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("customer_id")
@Expose
private Integer customerId;
@SerializedName("photo")
@Expose
private String photo;
@SerializedName("type")
@Expose
private Integer type;
@SerializedName("qualification")
@Expose
private String qualification;
@SerializedName("department")
@Expose
private Integer department;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("current_hospital_info")
@Expose
private List<CurrentHospitalInfo> currentHospitalInfo = null;
@SerializedName("previous_hospital_info")
@Expose
private List<PreviousHospitalInfo> previousHospitalInfo = null;

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public Integer getCustomerId() {
return customerId;
}

public void setCustomerId(Integer customerId) {
this.customerId = customerId;
}

public String getPhoto() {
return photo;
}

public void setPhoto(String photo) {
this.photo = photo;
}

public Integer getType() {
return type;
}

public void setType(Integer type) {
this.type = type;
}

public String getQualification() {
return qualification;
}

public void setQualification(String qualification) {
this.qualification = qualification;
}

public Integer getDepartment() {
return department;
}

public void setDepartment(Integer department) {
this.department = department;
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

public List<CurrentHospitalInfo> getCurrentHospitalInfo() {
return currentHospitalInfo;
}

public void setCurrentHospitalInfo(List<CurrentHospitalInfo> currentHospitalInfo) {
this.currentHospitalInfo = currentHospitalInfo;
}

public List<PreviousHospitalInfo> getPreviousHospitalInfo() {
return previousHospitalInfo;
}

public void setPreviousHospitalInfo(List<PreviousHospitalInfo> previousHospitalInfo) {
this.previousHospitalInfo = previousHospitalInfo;
}

}