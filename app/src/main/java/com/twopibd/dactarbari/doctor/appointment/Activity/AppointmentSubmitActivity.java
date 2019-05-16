package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppointmentSubmitActivity extends AppCompatActivity {
    @BindView(R.id.tv_drName)
    TextView tv_drName;
    @BindView(R.id.tv_time)
    TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_submit);
        ButterKnife.bind(this);
        tv_drName.setText(DataStore.selectedSearchModel.getName());
        tv_time.setText(DataStore.selectedSloat.getTime());
    }

    public void back(View view) {
        onBackPressed();
    }
}
