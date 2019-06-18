package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TodayJobList {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("userId")
@Expose
private Integer userId;
@SerializedName("companyId")
@Expose
private Integer companyId;
@SerializedName("contactPerson")
@Expose
private String contactPerson;
@SerializedName("designation")
@Expose
private String designation;
@SerializedName("phone")
@Expose
private String phone;
@SerializedName("email")
@Expose
private String email;
@SerializedName("jobTypeId")
@Expose
private String jobTypeId;
@SerializedName("address")
@Expose
private String address;
@SerializedName("followupDate")
@Expose
private String followupDate;
@SerializedName("note")
@Expose
private String note;
@SerializedName("adminNote")
@Expose
private String adminNote;
@SerializedName("verify")
@Expose
private String verify;
@SerializedName("location")
@Expose
private String location;
@SerializedName("image")
@Expose
private String image;
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

public Integer getUserId() {
return userId;
}

public void setUserId(Integer userId) {
this.userId = userId;
}

public Integer getCompanyId() {
return companyId;
}

public void setCompanyId(Integer companyId) {
this.companyId = companyId;
}

public String getContactPerson() {
return contactPerson;
}

public void setContactPerson(String contactPerson) {
this.contactPerson = contactPerson;
}

public String getDesignation() {
return designation;
}

public void setDesignation(String designation) {
this.designation = designation;
}

public String getPhone() {
return phone;
}

public void setPhone(String phone) {
this.phone = phone;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getJobTypeId() {
return jobTypeId;
}

public void setJobTypeId(String jobTypeId) {
this.jobTypeId = jobTypeId;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getFollowupDate() {
return followupDate;
}

public void setFollowupDate(String followupDate) {
this.followupDate = followupDate;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

public String getAdminNote() {
return adminNote;
}

public void setAdminNote(String adminNote) {
this.adminNote = adminNote;
}

public String getVerify() {
return verify;
}

public void setVerify(String verify) {
this.verify = verify;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
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