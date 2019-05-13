package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.twopibd.dactarbari.doctor.appointment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentSubmitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_submit);
        ButterKnife.bind(this);
    }

    public void back(View view) {
        onBackPressed();
    }
}
