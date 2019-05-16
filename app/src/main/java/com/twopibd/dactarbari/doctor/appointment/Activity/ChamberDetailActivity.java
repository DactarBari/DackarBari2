package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ScheduleAdapter;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.PHOTO_BASE;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.singleDrModel;


public class ChamberDetailActivity extends AppCompatActivity implements ApiListener.ChamberDetailsDownloadListener {
    ScheduleAdapter mAdapter;
    RecyclerView recycler_view;
    Context context = this;
    public TextView title, tv_hospitalName, tv_lastDegree, tv_epacialist, tv_address;
    SearchModel doctorModel;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.profile_image)
    CircleImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamber_detail);
        ButterKnife.bind(this);
        tv_hospitalName = (TextView) findViewById(R.id.tv_hospitalName);
        tv_lastDegree = (TextView) findViewById(R.id.tv_lastDegree);
        tv_epacialist = (TextView) findViewById(R.id.tv_epacialist);
        tv_address = (TextView) findViewById(R.id.tv_address);
        doctorModel = DataStore.selectedSearchModel;
        tv_name.setText(doctorModel.getName());
        tv_address.setText(doctorModel.getChamberAddress());
        tv_epacialist.setText(doctorModel.getDepartmentName());
        Picasso.with(context).load(PHOTO_BASE + doctorModel.getPhoto()).into(profile_image);
        downloadChamberDetails(String.valueOf(doctorModel.getChamberId()));
       // Toast.makeText(context, ""+doctorModel.getChamberId(), Toast.LENGTH_SHORT).show();


//        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
//        mAdapter = new ScheduleAdapter();
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
//        recycler_view.setLayoutManager(mLayoutManager);
//        recycler_view.setItemAnimator(new DefaultItemAnimator());
//        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//
//        recycler_view.setAdapter(mAdapter);
    }

    private void downloadChamberDetails(String chamberId) {
        MyProgressDialog.with(context);
        Api.getInstance().chamberDetails(chamberId,this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    public void openBookingActivity(View view) {
        startActivity(new Intent(this, SendBookingActivity.class));
    }

    public void back(View v) {
        onBackPressed();
    }



    @Override
    public void onChamberDetailsDownloadSuccess(ChamberInfo data) {
        MyProgressDialog.destroy();
        DataStore.chamberInfo=data;

        DataStore.seelctedscheduleInfo=data.getScheduleInfo();
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new ScheduleAdapter(data.getScheduleInfo());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

        recycler_view.setAdapter(mAdapter);
        //Toast.makeText(context, ""+data.getScheduleInfo().get(0).getDay(), Toast.LENGTH_SHORT).show();



    }

    @Override
    public void onChamberDetailsDownloadFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
