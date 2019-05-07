package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusResponse;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity  {
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_mobile)
    EditText ed_mobile;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_password)
    EditText ed_password;
    ProgressDialog progressDialog;
    String name, email, mobile, password;
    @BindView(R.id.linearDoctor)
    LinearLayout linearDoctor;
    @BindView(R.id.linearPaitent)
    LinearLayout linearPaitent;

    @BindView(R.id.img_doctor)
    ImageView img_doctor;
    @BindView(R.id.img_patient)
    ImageView img_patient;

    @BindView(R.id.tv_patient)
    TextView tv_patient;
    @BindView(R.id.tv_doctor)
    TextView tv_doctor;


    int DOCTOR = 0;
    int PATIENT = 1;
    int selctedUserType = PATIENT;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        updateTypeUI();
        linearDoctor.setOnClickListener((View v) -> moveToDoctor());
        linearPaitent.setOnClickListener((View v) -> moveToPatient());


    }

    private void moveToPatient() {
        selctedUserType = PATIENT;
        updateTypeUI();
    }

    private void moveToDoctor() {
        selctedUserType = DOCTOR;
        updateTypeUI();

    }

    private void updateTypeUI() {
        if (selctedUserType == DOCTOR) {
            setDoctorUI();
        } else if (selctedUserType == PATIENT) {
            setPatientUI();
        }
    }

    private void setPatientUI() {


        tv_patient.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        tv_doctor.setTextColor(context.getResources().getColor(R.color.tColor));


        int sdk = android.os.Build.VERSION.SDK_INT;

        img_doctor.setImageResource(R.drawable.doctor);
        img_patient.setImageResource(R.drawable.patient);

        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            linearPaitent.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_selected));
            linearDoctor.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_unselected));
        } else {
            linearPaitent.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_selected));
            linearDoctor.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_unselected));

        }
    }

    private void setDoctorUI() {

        img_doctor.setImageResource(R.drawable.doctor_color);
        img_patient.setImageResource(R.drawable.patient_grey);
        tv_doctor.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        tv_patient.setTextColor(context.getResources().getColor(R.color.tColor));


        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            linearDoctor.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_selected));
            linearPaitent.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_unselected));
        } else {
            linearDoctor.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_selected));
            linearPaitent.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_unselected));
        }
    }

    public void OpenHomeActivity(View view) {
        startActivity(new Intent(this, HomeActivity.class));
    }

    public void onSubmit(View view) {
        name = ed_name.getText().toString().trim();
        mobile = ed_mobile.getText().toString().trim();
        email = ed_email.getText().toString().trim();
        password = ed_password.getText().toString().trim();
        if (name.length() > 0 && mobile.length() > 0 && email.length() > 0 && password.length() > 0) {
            progressDialog.show();



        }
    }







}
