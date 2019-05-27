package com.twopibd.dactarbari.doctor.appointment.Model;

public class SelectedQualificationModel {
    boolean isSelected=false;
    QualificationModel qualificationModel;

    public SelectedQualificationModel(boolean isSelected, QualificationModel qualificationModel) {
        this.isSelected = isSelected;
        this.qualificationModel = qualificationModel;
    }

    public SelectedQualificationModel() {
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public QualificationModel getQualificationModel() {
        return qualificationModel;
    }

    public void setQualificationModel(QualificationModel qualificationModel) {
        this.qualificationModel = qualificationModel;
    }
}
