package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ChamberDaysAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ChamberListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChamberDetailsActivityDr extends AppCompatActivity implements ApiListener.drChamberDeleteListener,
        MyDialogList.ScheduleDialogListener,
        ApiListener.drScheduleAddListener {
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_fees)
    TextView tv_fees;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    String key;
    String chamber_id;
    SessionManager sessionManager;
    ChamberDaysAdapterDoctor mAdapter;
    Day addeedDay = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamber_details_dr);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        ChamberModel dataModel = Data.chamberModel;
        chamber_id = "" + dataModel.getId();
        key = sessionManager.getToken();
        tv_address.setText(dataModel.getChamberAddress());
        tv_fees.setText("" + dataModel.getFee());

        setUpRecycler(dataModel.getScheduleInfo());
    }

    private void setUpRecycler(List<ScheduleInfo> scheduleInfo) {
        mAdapter = new ChamberDaysAdapterDoctor(scheduleInfo);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));

        recycler_view.setAdapter(mAdapter);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void deleteChamber(View view) {
        MyProgressDialog.with(this);
        Api.getInstance().deleteChamber(key, chamber_id, this);
    }

    @Override
    public void onChamberDeleteSuccess(StatusMessage data) {
        MyProgressDialog.destroy();
        Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void onChamberDeleteFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    public void addDialog(View view) {
        MyDialogList.getInstance().with(ChamberDetailsActivityDr.this).addScheduleDialog(this);
    }

    @Override
    public void onYesDialogClicked(boolean result, Day day) {
        addeedDay = day;
        Gson gson = new Gson();
        MyProgressDialog.with(this);
        Api.getInstance().AddSchedule(key, chamber_id, gson.toJson(day), this);
    }

    @Override
    public void onNoDialogClicked(boolean result) {
        Toast.makeText(this, "canceled", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void ondrScheduleAddSuccess(StatusMessage data) {
        MyProgressDialog.destroy();
        Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, ChamberListActivityDr.class));

    }

    @Override
    public void ondrScheduleAddFailed(String msg) {
        MyProgressDialog.destroy();


    }
}
