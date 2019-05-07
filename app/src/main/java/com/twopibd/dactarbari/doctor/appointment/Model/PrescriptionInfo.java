package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.PHOTO_BASE;

public class PrescriptionInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("patient_id")
@Expose
private Integer patientId;
@SerializedName("prescription")
@Expose
private String prescription;
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

public Integer getPatientId() {
return patientId;
}

public void setPatientId(Integer patientId) {
this.patientId = patientId;
}

public String getPrescription() {
return prescription;
}

public void setPrescription(String prescription) {
this.prescription = prescription;
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

    public PrescriptionInfo(Integer id, Integer patientId, String prescription, String createdAt, String updatedAt) {
        this.id = id;
        this.patientId = patientId;
        this.prescription = prescription;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}