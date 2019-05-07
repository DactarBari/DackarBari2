package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ChamberDaysAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.NewScheduleAddAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddWeeklyChamber2 extends AppCompatActivity implements MyDialogList.ScheduleDialogListener{
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    List<Day> list=new ArrayList<>();
    NewScheduleAddAdapterDoctor mAdapter;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weekly_chamber2);
        ButterKnife.bind(this);
        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        mAdapter = new NewScheduleAddAdapterDoctor(context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);
    }

    public void back(View view) {
        onBackPressed();
    }

    public void openMap(View view) {
    }

    public void SaveSchedule(View view) {
    }

    public void openAddDialog(View view) {
        MyDialogList.getInstance().with(AddWeeklyChamber2.this).addScheduleDialog(this);

    }

    @Override
    public void onYesDialogClicked(boolean result, Day day) {
        mAdapter.addToAdapter(day);
        Gson gson=new Gson();
        Toast.makeText(this, gson.toJson(list), Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNoDialogClicked(boolean result) {

    }
}
