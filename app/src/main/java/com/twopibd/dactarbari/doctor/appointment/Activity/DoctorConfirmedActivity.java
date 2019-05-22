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

import com.twopibd.dactarbari.doctor.appointment.Adapter.ConfirmedAppointmentAdapterDoctor;
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

public class DoctorConfirmedActivity extends AppCompatActivity  {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.liner_no_item)
    LinearLayout liner_no_item;
    SessionManager sessionManager;
    String USER_ID,key;
    Context context=this;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_confirmed);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        USER_ID=sessionManager.getUserId();
        key=sessionManager.getToken();
        ConfirmedAppointmentAdapterDoctor mAdapter = new ConfirmedAppointmentAdapterDoctor(HomeActivityDoctor.CONFIRMED_LIST);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
       // recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);
        if (HomeActivityDoctor.CONFIRMED_LIST.size()>0){
            liner_no_item.setVisibility(View.GONE);
        }else {
            liner_no_item.setVisibility(View.VISIBLE);

        }
    }



    public void back(View view) {
        onBackPressed();
    }
}
