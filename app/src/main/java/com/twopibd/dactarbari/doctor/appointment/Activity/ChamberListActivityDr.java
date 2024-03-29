package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
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
    @BindView(R.id.liner_no_item)
    LinearLayout liner_no_item;

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
        if (data != null &&data.size()>0) {
            MyProgressDialog.destroy();
            ChamberListAdapterDoctor mAdapter = new ChamberListAdapterDoctor(data);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL);
            recycler_view.setLayoutManager(_sGridLayoutManager);
            recycler_view.setItemAnimator(new DefaultItemAnimator());
            //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

            recycler_view.setAdapter(mAdapter);
            liner_no_item.setVisibility(View.GONE);

        } else {
            liner_no_item.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onChamberDownloadFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
