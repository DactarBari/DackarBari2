package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentSearchModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyProgressBar;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.changeDateformate1;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.selectedSearchAppointmentModel;

public class ServingActivityDr extends BaseActivity implements ApiListener.servePostListener {
    @BindView(R.id.tv_name)
    TextView tv_name;


    @BindView(R.id.tv_problem)
    TextView tv_problem;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_sloat)
    TextView tv_sloat;
    @BindView(R.id.tv_date)
    TextView tv_date;
    AppointmentSearchModel data;
    SessionManager sessionManager;
    String KEY;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serving_dr);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        KEY = sessionManager.getToken();
        data = selectedSearchAppointmentModel;
        tv_name.setText(data.getAppointmentFor());
        tv_problem.setText(data.getCurrentProblems());
        tv_date.setText(data.getDate());
        tv_address.setText(data.getChamberAddress());
        tv_sloat.setText(changeDateformate1(data.getSlotStartTime()) + "--" + changeDateformate1(data.getSlotEndTime()));
        tv_status.setText(DataStore.convertAppointmentStatus(""+data.getStatus()));
    }

    public void back(View view) {
        onBackPressed();
    }

    public void servePost(View view) {
        startActivity(new Intent(this,PrescriptionGivingActivity.class));

    }

    @Override
    public void onservePostSuccess(StatusMessage data) {
        MyProgressBar.dismiss();
        Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    @Override
    public void onservePostFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(context, "Error occured.Please try again", Toast.LENGTH_SHORT).show();
        onBackPressed();


    }

    public void openHistory(View view) {
    }
}
