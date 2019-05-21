package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.DoctorListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.PendingAppointmentAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrPendingAppointments extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    SessionManager sessionManager;
    String USER_ID,key;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_pending_appointments);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        USER_ID=sessionManager.getUserId();
        key=sessionManager.getToken();
        //Api.getInstance().getAppointmentsByDoctor(key,USER_ID,"doctor","0",this);
        PendingAppointmentAdapterDoctor mAdapter = new PendingAppointmentAdapterDoctor(HomeActivityDoctor.PENDING_LIST);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);
    }


    public void back(View view) {
        onBackPressed();
    }
}
