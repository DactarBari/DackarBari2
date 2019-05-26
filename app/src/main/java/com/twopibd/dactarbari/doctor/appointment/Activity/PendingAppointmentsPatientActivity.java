package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.PendingAppointmentAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.PendingAppointmentAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PendingAppointmentsPatientActivity extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    SessionManager sessionManager;
    String USER_ID, key;
    Context context = this;
    @BindView(R.id.liner_no_item)
    LinearLayout liner_no_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_appointments_patient);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        USER_ID = sessionManager.getUserId();
        key = sessionManager.getToken();
        Api.getInstance().getAppointmentsByDoctor(key, USER_ID, "patient", "0", this);
        //Toast.makeText(context, USER_ID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAppointmentDownloadSuccess(List<AppointmentModels> data) {
         //Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();


        if (data != null && data.size() > 0) {
            liner_no_item.setVisibility(View.GONE);
            PendingAppointmentAdapterPatient mAdapter = new PendingAppointmentAdapterPatient(data);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
            recycler_view.setLayoutManager(mLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            // recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));
            recycler_view.setAdapter(mAdapter);
        } else {
            liner_no_item.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    public void back(View view) {
        onBackPressed();
    }
}
