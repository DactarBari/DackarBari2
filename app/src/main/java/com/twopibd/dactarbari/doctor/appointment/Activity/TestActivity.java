package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TestActivity extends AppCompatActivity {
    Calendar calendar;
    int date, month;
    @BindView(R.id.tv_date)
    TextView tv_date;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        calendar = Calendar.getInstance();
        date = calendar.getTime().getDate();
        month = calendar.getTime().getMonth();
        tv_date.setText("" + calendar.getTime().getDate()+" "+DataStore.convertToMonth(calendar.getTime().getMonth()));
    }
}
