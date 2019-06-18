package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssistantOnlineModel {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("customer_id")
@Expose
private Integer customerId;
@SerializedName("doctor_id")
@Expose
private Integer doctorId;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("assistant_profile")
@Expose
private AssistantProfile assistantProfile;

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

public Integer getDoctorId() {
return doctorId;
}

public void setDoctorId(Integer doctorId) {
this.doctorId = doctorId;
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

public AssistantProfile getAssistantProfile() {
return assistantProfile;
}

public void setAssistantProfile(AssistantProfile assistantProfile) {
this.assistantProfile = assistantProfile;
}

}