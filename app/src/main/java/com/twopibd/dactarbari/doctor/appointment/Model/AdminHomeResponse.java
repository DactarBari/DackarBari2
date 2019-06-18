package com.twopibd.dactarbari.doctor.appointment.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AdminHomeResponse {

@SerializedName("pendingJOb")
@Expose
private Integer pendingJOb;
@SerializedName("totalJOb")
@Expose
private Integer totalJOb;
@SerializedName("message")
@Expose
private Integer message;
@SerializedName("level_1")
@Expose
private Integer level1;
@SerializedName("level_2")
@Expose
private Integer level2;
@SerializedName("todayJobListCount")
@Expose
private Integer todayJobListCount;
@SerializedName("todayJobList")
@Expose
private List<TodayJobList> todayJobList = null;

public Integer getPendingJOb() {
return pendingJOb;
}

public void setPendingJOb(Integer pendingJOb) {
this.pendingJOb = pendingJOb;
}

public Integer getTotalJOb() {
return totalJOb;
}

public void setTotalJOb(Integer totalJOb) {
this.totalJOb = totalJOb;
}

public Integer getMessage() {
return message;
}

public void setMessage(Integer message) {
this.message = message;
}

public Integer getLevel1() {
return level1;
}

public void setLevel1(Integer level1) {
this.level1 = level1;
}

public Integer getLevel2() {
return level2;
}

public void setLevel2(Integer level2) {
this.level2 = level2;
}

public Integer getTodayJobListCount() {
return todayJobListCount;
}

public void setTodayJobListCount(Integer todayJobListCount) {
this.todayJobListCount = todayJobListCount;
}

public List<TodayJobList> getTodayJobList() {
return todayJobList;
}

public void setTodayJobList(List<TodayJobList> todayJobList) {
this.todayJobList = todayJobList;
}

}