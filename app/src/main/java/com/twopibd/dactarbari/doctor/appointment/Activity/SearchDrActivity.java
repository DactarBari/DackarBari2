package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.twopibd.dactarbari.doctor.appointment.R;

public class SearchDrActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dr);
    }

    public void back(View view) {
        onBackPressed();
    }
}
