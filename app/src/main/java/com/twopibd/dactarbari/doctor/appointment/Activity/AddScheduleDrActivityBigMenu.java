package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.twopibd.dactarbari.doctor.appointment.R;

public class AddScheduleDrActivityBigMenu extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule_dr_big_menu);
    }

    public void addDailySchedule(View view) {
        startActivity(new Intent(this,AddDailyScheduleActivityDr.class));
    }

    public void addWeeklySchedule(View view) {
        startActivity(new Intent(this,AddWeeklyChamberActivity.class));

    }

    public void back(View view) {
        onBackPressed();
    }

    public void addMonthlySchedule(View view) {
    }
}
