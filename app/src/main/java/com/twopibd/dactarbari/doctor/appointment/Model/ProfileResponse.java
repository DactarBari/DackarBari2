package com.twopibd.dactarbari.doctor.appointment.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProfileResponse {

@SerializedName("country_info")
@Expose
private List<CountryInfo> countryInfo = null;
@SerializedName("hospital_info")
@Expose
private List<HospitalModel> hospitalInfo = null;
@SerializedName("department_info")
@Expose
private List<DepartmentInfo> departmentInfo = null;
@SerializedName("doctor_info")
@Expose
private DoctorInfo doctorInfo=null;
@SerializedName("patient_info")
@Expose
private PatientInfo patientInfo=null;

public List<CountryInfo> getCountryInfo() {
return countryInfo;
}

public void setCountryInfo(List<CountryInfo> countryInfo) {
this.countryInfo = countryInfo;
}

public List<HospitalModel> getHospitalInfo() {
return hospitalInfo;
}

public void setHospitalInfo(List<HospitalModel> hospitalInfo) {
this.hospitalInfo = hospitalInfo;
}

public List<DepartmentInfo> getDepartmentInfo() {
return departmentInfo;
}

public void setDepartmentInfo(List<DepartmentInfo> departmentInfo) {
this.departmentInfo = departmentInfo;
}

public DoctorInfo getDoctorInfo() {
return doctorInfo;
}

public void setDoctorInfo(DoctorInfo doctorInfo) {
this.doctorInfo = doctorInfo;
}

public PatientInfo getPatientInfo() {
return patientInfo;
}

public void setPatientInfo(PatientInfo patientInfo) {
this.patientInfo = patientInfo;
}

}