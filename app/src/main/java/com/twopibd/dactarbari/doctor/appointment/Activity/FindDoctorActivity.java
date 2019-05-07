package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.SpacialistAdapter;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.BasicInfoModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SpacialistModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.searchResult;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.spacialist;


public class FindDoctorActivity extends AppCompatActivity implements ApiListener.basicInfoDownloadListener,
        ApiListener.doctorSearchListener {

    RecyclerView recyclerView;
    SpacialistAdapter mAdapter;
    Context context = this;

    Spinner spinner;
    String locationCity = "Dhaka";
    List<String> hospitalList = new ArrayList<>();
    ArrayAdapter<String> dataAdapter;
    EditText ed_query;
    String selectedHospital = "";
    String selectedSpacialist = "";
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        ed_query = (EditText) findViewById(R.id.ed_query);

        mAdapter = new SpacialistAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);



        spinner = (Spinner) findViewById(R.id.hospitals);
        dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, hospitalList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Api.getInstance().downloadBasicInfo(this);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedHospital = hospitalList.get(i);

                } else {
                    selectedHospital = "";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedHospital = "";

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }



    @Override
    public void onBasicInfoDownloadSuccess(BasicInfoModel data) {
        spacialist.clear();
        hospitalList.add("Search by hospital");
        for (int i = 0; i < data.getSpacialist().size(); i++) {
            spacialist.add(new SpacialistModel(data.getSpacialist().get(i), false));
        }
        mAdapter.notifyDataSetChanged();
        hospitalList.addAll(data.getHospitalList());
        dataAdapter.notifyDataSetChanged();


    }

    @Override
    public void onBasicInfoDownloadFailed(String msg) {

    }

    public void searchDoctor(View view) {
        progressDialog.show();
        String searchQuery = "";
        selectedSpacialist = "";
        //selectedHospital="";
        searchQuery += ed_query.getText().toString().trim() + "\n";
        String name = ed_query.getText().toString().trim();
        searchQuery += selectedHospital + "\n";
        for (int i = 0; i < spacialist.size(); i++) {
            if (spacialist.get(i).isSelected()) {
                selectedSpacialist = spacialist.get(i).getName();
                break;

            }
        }
        searchQuery += selectedSpacialist;
        // Toast.makeText(context, searchQuery, Toast.LENGTH_LONG).show();
        Api.getInstance().searchDoctor(name, selectedHospital, selectedSpacialist, "", "", this);


    }

    @Override
    public void onSearchSuccess(List<DoctorModel> list) {
        searchResult.clear();
        progressDialog.dismiss();
        searchResult.addAll(list);
        startActivity(new Intent(this, SearchResultActivity.class));
    }

    @Override
    public void onSuccessFailed(String msg) {
        progressDialog.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
