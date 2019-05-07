package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Adapter.ScheduleAdapter;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.singleDrModel;


public class ChamberDetailActivity extends AppCompatActivity {
    ScheduleAdapter mAdapter;
    RecyclerView recycler_view;
    Context context = this;
    public TextView title, tv_hospitalName,  tv_lastDegree, tv_epacialist, tv_address;
    DoctorModel doctorModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamber_detail);
        tv_hospitalName = (TextView) findViewById(R.id.tv_hospitalName);
        tv_lastDegree = (TextView) findViewById(R.id.tv_lastDegree);
        tv_epacialist = (TextView) findViewById(R.id.tv_epacialist);
        tv_address = (TextView) findViewById(R.id.tv_address);
        doctorModel=singleDrModel;
        setTitle(doctorModel.getDrName());
        tv_address.setText(doctorModel.getAddress());
        tv_epacialist.setText(doctorModel.getSpecialist());
        tv_hospitalName.setText(doctorModel.getHospitalName());
        tv_address.setText(doctorModel.getAddress());

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ScheduleAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));

        recycler_view.setAdapter(mAdapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }

    public void openBookingActivity(View view) {
        startActivity(new Intent(this,SendBookingActivity.class));
    }
}
