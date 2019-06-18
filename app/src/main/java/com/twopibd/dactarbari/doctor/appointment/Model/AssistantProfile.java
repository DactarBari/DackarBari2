package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AssistantProfile {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("user_type")
@Expose
private String userType;
@SerializedName("name")
@Expose
private String name;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("house_no")
@Expose
private String houseNo;
@SerializedName("street_address")
@Expose
private String streetAddress;
@SerializedName("country")
@Expose
private String country;
@SerializedName("area")
@Expose
private String area;
@SerializedName("city")
@Expose
private String city;
@SerializedName("postcode")
@Expose
private String postcode;
@SerializedName("email")
@Expose
private String email;
@SerializedName("mobile")
@Expose
private String mobile;
@SerializedName("token")
@Expose
private String token;
@SerializedName("status")
@Expose
private Integer status;
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

public String getUserType() {
return userType;
}

public void setUserType(String userType) {
this.userType = userType;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public String getHouseNo() {
return houseNo;
}

public void setHouseNo(String houseNo) {
this.houseNo = houseNo;
}

public String getStreetAddress() {
return streetAddress;
}

public void setStreetAddress(String streetAddress) {
this.streetAddress = streetAddress;
}

public String getCountry() {
return country;
}

public void setCountry(String country) {
this.country = country;
}

public String getArea() {
return area;
}

public void setArea(String area) {
this.area = area;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getPostcode() {
return postcode;
}

public void setPostcode(String postcode) {
this.postcode = postcode;
}

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public String getMobile() {
return mobile;
}

public void setMobile(String mobile) {
this.mobile = mobile;
}

public String getToken() {
return token;
}

public void setToken(String token) {
this.token = token;
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

}