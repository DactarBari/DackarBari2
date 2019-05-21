package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.twopibd.dactarbari.doctor.appointment.R;

public class ScheduleMenuActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_menu);
    }

    public void openScheduleList(View view) {
    }

    public void addScheduleDr(View view) {
        startActivity(new Intent(this, AddScheduleDrActivityBigMenu.class));
    }

    public void ViewScheduleDr(View view) {
        startActivity(new Intent(this, ChamberListActivityDr.class));
    }


}
