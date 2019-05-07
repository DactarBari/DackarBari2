package com.twopibd.dactarbari.doctor.appointment.Model;

public class SelectedHospitalsModel {
    HospitalModel hospitalModel;
    boolean isSelected;

    public SelectedHospitalsModel(HospitalModel hospitalModel, boolean isSelected) {
        this.hospitalModel = hospitalModel;
        this.isSelected = isSelected;
    }

    public SelectedHospitalsModel() {
    }

    public HospitalModel getHospitalModel() {
        return hospitalModel;
    }

    public void setHospitalModel(HospitalModel hospitalModel) {
        this.hospitalModel = hospitalModel;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
