package com.twopibd.dactarbari.doctor.appointment.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserInfo {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_type")
    @Expose
    private String userType=null;
    @SerializedName("name")
    @Expose
    private String name=null;
    @SerializedName("gender")
    @Expose
    private String gender=null;
    @SerializedName("house_no")
    @Expose
    private String houseNo=null;
    @SerializedName("street_address")
    @Expose
    private String streetAddress=null;
    @SerializedName("country")
    @Expose
    private String country=null;
    @SerializedName("area")
    @Expose
    private String area=null;
    @SerializedName("city")
    @Expose
    private String city=null;
    @SerializedName("postcode")
    @Expose
    private String postcode=null;
    @SerializedName("email")
    @Expose
    private String email=null;
    @SerializedName("mobile")
    @Expose
    private String mobile=null;
    @SerializedName("token")
    @Expose
    private Object token=null;
    @SerializedName("status")
    @Expose
    private Integer status=null;
    @SerializedName("created_at")
    @Expose
    private String createdAt=null;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt=null;
    @SerializedName("photo")
    @Expose
    private String photo=null;

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

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}