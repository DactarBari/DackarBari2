package com.twopibd.dactarbari.doctor.appointment.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HospitalInfo {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("division_id")
@Expose
private Integer divisionId;
@SerializedName("zilla_id")
@Expose
private Integer zillaId;
@SerializedName("upozilla_id")
@Expose
private Integer upozillaId;
@SerializedName("road_house")
@Expose
private String roadHouse;
@SerializedName("photo")
@Expose
private String photo;
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

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public Integer getDivisionId() {
return divisionId;
}

public void setDivisionId(Integer divisionId) {
this.divisionId = divisionId;
}

public Integer getZillaId() {
return zillaId;
}

public void setZillaId(Integer zillaId) {
this.zillaId = zillaId;
}

public Integer getUpozillaId() {
return upozillaId;
}

public void setUpozillaId(Integer upozillaId) {
this.upozillaId = upozillaId;
}

public String getRoadHouse() {
return roadHouse;
}

public void setRoadHouse(String roadHouse) {
this.roadHouse = roadHouse;
}

public String getPhoto() {
return photo;
}

public void setPhoto(String photo) {
this.photo = photo;
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