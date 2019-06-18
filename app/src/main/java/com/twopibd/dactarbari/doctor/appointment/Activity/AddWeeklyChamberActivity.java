package com.twopibd.dactarbari.doctor.appointment.Activity;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.twopibd.dactarbari.doctor.appointment.Adapter.DaysTimesAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.NewScheduleAddAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Data.locationPickListener;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.DaysTimeModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;


import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.newSchedulelist;

public class AddWeeklyChamberActivity extends AppCompatActivity implements ApiListener.drSchedulePostListener, MyDialogList.ScheduleDialogListener {




    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    public static List<DaysTimeModel> Dayslist = new ArrayList<>();
    SessionManager sessionManager;
    String key = "";
    Context context = this;
    String chamber_latitude = "";
    String chamber_longitude = "";
    String address = "";
    public static List<Day> daysToAdd = new ArrayList<>();
    String dd = "";
    NewScheduleAddAdapterDoctor mAdapter;
    public static List<String> day_ = new ArrayList<>();
    public static List<String> start_ = new ArrayList<>();
    public static List<String> end_ = new ArrayList<>();
    public static List<String> capacity_ = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_weekly_chamber);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        key = sessionManager.getToken();
        newSchedulelist.clear();
        init_days_();


    }

    private void init_days_() {
        mAdapter = new NewScheduleAddAdapterDoctor(context);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);


    }


    public void SaveSchedule(View view) {
        MyProgressDialog.with(this);

        String address = ChamberAddCommonInfoActivity.ADDRESS;
        String fees = ChamberAddCommonInfoActivity.FEES;


        Gson gson = new Gson();
        List<Day> okokok = new ArrayList<>();
        for (int i = 0; i < day_.size(); i++) {
            okokok.add(new Day(day_.get(i), start_.get(i), end_.get(i), capacity_.get(i)));
        }
        String lat=null;
        String log=null;
        if (chamber_latitude!=null && chamber_longitude!=null){
            lat=chamber_latitude;
            log=chamber_longitude;

        }
        Api.getInstance().setDrSchedule(key, sessionManager.getUserId(), address, fees, gson.toJson(okokok), lat, log, this);
        // Toast.makeText(context,gson.toJson(daysToAdd) , Toast.LENGTH_LONG).show();

    }

    @Override
    public void ondrSchedulePostSuccess(StatusMessage data) {
        MyProgressDialog.destroy();
        Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(context,HomeActivityDoctor.class));
        finishAffinity();


    }

    @Override
    public void ondrSchedulePostFailed(String msg) {
        MyProgressDialog.destroy();

        Toast.makeText(this, "Error occured.Try again later", Toast.LENGTH_SHORT).show();
        onBackPressed();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void back(View view) {
        onBackPressed();
    }

    public void openMap(View view) {
        startActivity(new Intent(this, MapsActivity3.class));
    }

    public void openAddDialog(View view) {
        MyDialogList.getInstance().with(AddWeeklyChamberActivity.this).addScheduleDialog(this);

    }

    @Override
    public void onYesDialogClicked(boolean result, Day day) {
        mAdapter.addToAdapter(day);
        daysToAdd.add(day);
        day_.add(day.getDay());
        start_.add(day.getStart_time());
        end_.add(day.getEnd_time());
        capacity_.add(day.getPatient_capacity());


    }

    @Override
    public void onNoDialogClicked(boolean result) {

    }

}