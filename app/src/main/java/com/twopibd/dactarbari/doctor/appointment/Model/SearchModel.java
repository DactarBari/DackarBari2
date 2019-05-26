package com.twopibd.dactarbari.doctor.appointment.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchModel {

    @SerializedName("doctor_id")
    @Expose
    private Integer doctorId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photo")
    @Expose
    private String photo;
    @SerializedName("department_name")
    @Expose
    private String departmentName;
    @SerializedName("chamber_id")
    @Expose
    private Integer chamberId;
    @SerializedName("chamber_address")
    @Expose
    private String chamberAddress;
    @SerializedName("chamber_latitude")
    @Expose
    private String chamberLatitude;
    @SerializedName("chamber_longitude")
    @Expose
    private String chamberLongitude;
    @SerializedName("current_hospitals")
    @Expose
    private List<CurrentHospital> currentHospitals = null;

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getChamberId() {
        return chamberId;
    }

    public void setChamberId(Integer chamberId) {
        this.chamberId = chamberId;
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

    public List<CurrentHospital> getCurrentHospitals() {
        return currentHospitals;
    }

    public void setCurrentHospitals(List<CurrentHospital> currentHospitals) {
        this.currentHospitals = currentHospitals;
    }

}