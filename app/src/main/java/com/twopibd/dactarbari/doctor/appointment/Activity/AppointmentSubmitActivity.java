package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonElement;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentSubmitActivity extends AppCompatActivity implements ApiListener.appointmentPostListener {
    @BindView(R.id.tv_drName)
    TextView tv_drName;
    @BindView(R.id.ed_appointmentFor)
    EditText ed_appointmentFor;
    @BindView(R.id.ed_currentProblems)
    EditText ed_currentProblems;
    @BindView(R.id.tv_time)
    TextView tv_time;
    String startTime;
    String endTime;
    SessionManager sessionManager;
    String USER_ID;
    String appointmentFor;
    String currentProblems;
    String selectedAppointmentDate;
    String selectedSchedule;
    Context context = this;
    String TOKEN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_submit);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        USER_ID = sessionManager.getUserId();
        TOKEN = sessionManager.getToken();
        tv_drName.setText(DataStore.selectedSearchModel.getName());
        tv_time.setText(DataStore.selectedSloat.getTime());
        Toast.makeText(this, DataStore.selectedSloat.getTime(), Toast.LENGTH_SHORT).show();
        startTime = DataStore.selectedSloat.getTime().substring(0, 7);
        endTime = DataStore.selectedSloat.getTime().substring(9, 16);
        selectedAppointmentDate = DataStore.selectedDate;
        selectedSchedule = DataStore.selectedSloat.getSchedule_id();
    }

    public void back(View view) {
        onBackPressed();
    }

    public void submit(View view) {
        currentProblems = ed_currentProblems.getText().toString().trim();
        appointmentFor = ed_appointmentFor.getText().toString().trim();
        if (appointmentFor.length() == 0) {
            appointmentFor = sessionManager.getUserName();
        }

        MyProgressDialog.with(context);
        //Toast.makeText(context, ""+selectedSchedule+"\n"+startTime+"\n"+endTime+"\n"+USER_ID+"\n"+appointmentFor+"\n"+currentProblems+"\n"+selectedAppointmentDate, Toast.LENGTH_LONG).show();

        Api.getInstance().postAppointment(TOKEN, selectedSchedule, startTime, endTime, USER_ID, appointmentFor, currentProblems, selectedAppointmentDate, this);

    }

    @Override
    public void onAppointmentPostSuccess(StatusMessage data) {
        MyProgressDialog.destroy();
        Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this,PatientActivityMap.class));
        finishAffinity();
    }

    @Override
    public void onAppointmentPostFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(context,  msg, Toast.LENGTH_SHORT).show();

    }
}
