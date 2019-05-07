package com.twopibd.dactarbari.doctor.appointment.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PatientInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("customer_id")
@Expose
private Integer customerId;
@SerializedName("current_problems")
@Expose
private String currentProblems;
@SerializedName("created_at")
@Expose
private String createdAt;
@SerializedName("updated_at")
@Expose
private String updatedAt;
@SerializedName("prescription_info")
@Expose
private List<PrescriptionInfo> prescriptionInfo = null;

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

public String getCurrentProblems() {
return currentProblems;
}

public void setCurrentProblems(String currentProblems) {
this.currentProblems = currentProblems;
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

public List<PrescriptionInfo> getPrescriptionInfo() {
return prescriptionInfo;
}

public void setPrescriptionInfo(List<PrescriptionInfo> prescriptionInfo) {
this.prescriptionInfo = prescriptionInfo;
}

}