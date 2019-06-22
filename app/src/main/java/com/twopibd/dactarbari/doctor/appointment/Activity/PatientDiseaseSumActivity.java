package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.twopibd.dactarbari.doctor.appointment.R;

public class PatientDiseaseSumActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_disease_sum);
    }

    public void back(View view) {
        onBackPressed();
    }
}
