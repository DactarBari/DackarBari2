package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.ChamberListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.DaysTimesAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChamberListActivityDr extends AppCompatActivity implements ApiListener.drChamberDownloadListener {
    SessionManager sessionManager;
    String user_id;
    String key;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamber_list_dr);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        user_id = sessionManager.getUserId();
        key = sessionManager.getToken();
        MyProgressDialog.with(ChamberListActivityDr.this);
        Api.getInstance().drChambers(key, user_id, this);
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void ondrChamberDownloadSuccess(List<ChamberModel> data) {
        MyProgressDialog.destroy();
        ChamberListAdapterDoctor  mAdapter = new ChamberListAdapterDoctor(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

        recycler_view.setAdapter(mAdapter);
    }

    @Override
    public void onChamberDownloadFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
