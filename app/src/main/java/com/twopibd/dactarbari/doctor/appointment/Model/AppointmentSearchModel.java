package com.twopibd.dactarbari.doctor.appointment.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppointmentSearchModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("schedule_id")
    @Expose
    private Integer scheduleId;
    @SerializedName("patient_id")
    @Expose
    private Integer patientId;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("slot_start_time")
    @Expose
    private String slotStartTime;
    @SerializedName("slot_end_time")
    @Expose
    private String slotEndTime;
    @SerializedName("current_problems")
    @Expose
    private String currentProblems;
    @SerializedName("appointment_for")
    @Expose
    private String appointmentFor;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("chamber_address")
    @Expose
    private String chamberAddress;
    @SerializedName("patient_name")
    @Expose
    private String patientName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Integer scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSlotStartTime() {
        return slotStartTime;
    }

    public void setSlotStartTime(String slotStartTime) {
        this.slotStartTime = slotStartTime;
    }

    public String getSlotEndTime() {
        return slotEndTime;
    }

    public void setSlotEndTime(String slotEndTime) {
        this.slotEndTime = slotEndTime;
    }

    public String getCurrentProblems() {
        return currentProblems;
    }

    public void setCurrentProblems(String currentProblems) {
        this.currentProblems = currentProblems;
    }

    public String getAppointmentFor() {
        return appointmentFor;
    }

    public void setAppointmentFor(String appointmentFor) {
        this.appointmentFor = appointmentFor;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getChamberAddress() {
        return chamberAddress;
    }

    public void setChamberAddress(String chamberAddress) {
        this.chamberAddress = chamberAddress;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

}