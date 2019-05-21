package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.PatientPendingHorizontalAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.WhatYouAreLookingAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.Model.FeatureType;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientNewActivity extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    Context context = this;
    String USER_ID, key;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_new);
        ButterKnife.bind(this);
        init_lookingFor();
        sessionManager = new SessionManager(this);
        USER_ID = sessionManager.getUserId();
        key = sessionManager.getToken();
        Api.getInstance().getAppointmentsByDoctor(key, USER_ID, "patient", "0", this);

    }

    private void init_lookingFor() {
        List<FeatureType> list = new ArrayList<>();
        list.add(new FeatureType("Doctor", "Book", "appointment", R.drawable.doctor_female));
        list.add(new FeatureType("Medicines ", "Buy", "health product", R.drawable.medicine));


        WhatYouAreLookingAdapterPatient mAdapter = new WhatYouAreLookingAdapterPatient(list);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onAppointmentDownloadSuccess(List<AppointmentModels> data) {
        // Toast.makeText(context, "" + data.size(), Toast.LENGTH_SHORT).show();
        PatientPendingHorizontalAdapterDoctor mAdapter = new PatientPendingHorizontalAdapterDoctor(data);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
//        recycler_viewPending.setLayoutManager(layoutManager);
//        recycler_viewPending.setItemAnimator(new DefaultItemAnimator());
//        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));
//
//        recycler_viewPending.setAdapter(mAdapter);
    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public void Logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();

    }

    public void mapActivity(View view) {
        startActivity(new Intent(this, PatientActivityMap.class));

    }

    public void openPendingPatient(View view) {
        startActivity(new Intent(this, PendingAppointmentsPatientActivity.class));

    }

    public void ConfirmedAppointmentsPatientActivity(View view) {
        startActivity(new Intent(this, ConfirmedAppointmentsPatientActivity.class));

    }
}
