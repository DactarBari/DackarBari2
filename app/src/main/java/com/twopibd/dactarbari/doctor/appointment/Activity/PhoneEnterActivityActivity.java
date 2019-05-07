package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twopibd.dactarbari.doctor.appointment.R;


public class PhoneEnterActivityActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_enter_activity);
    }

    public void openVarificationCodeActivity(View view) {
        startActivity(new Intent(this,CodeVerificationActivity.class));
    }
}
