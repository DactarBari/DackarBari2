package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.cachedCountryList;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.cachedDeparmentsList;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.cachedHospitalsList;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.hospitalNameBodyMap;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.getUserTypeList;

public class SignUpTypeActivity extends BaseActivity implements
        ApiListener.countryListDownlaodListener,
        ApiListener.HospitalListDownlaodListener,
        ApiListener.DepartmentListDownlaodListener{
    @BindView(R.id.spinner)
    Spinner spinner;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_type);
        ButterKnife.bind(this);
        Api.getInstance().downloadCountry(this);
        Api.getInstance().downloadHospitals(this);
        Api.getInstance().downloadDepartments(this);

        setUpTypeSpinner();
    }

    private void setUpTypeSpinner() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getUserTypeList());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        setUpListener();
    }

    private void setUpListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i>0){
                    if (i==1){
                        startActivity(new Intent(context,PatientSignupActivity.class));
                    }else if (i==2){
                        startActivity(new Intent(context,DrSignUpActivity.class));

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
    @Override
    public void onCountryDownloadSuccess(List<CountryModel> list) {
//        MyDialog.getInstance().with(SignUpTypeActivity.this)
//                .message("Downloaded country size "+list.size())
//                .autoBack(false)
//                .autoDismiss(false)
//                .show();

        cachedCountryList.clear();
        cachedCountryList.addAll(list);

        //Toast.makeText(context, " country "+list.size(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onCountryDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
    public  void back(View view){
        onBackPressed();
    }

    @Override
    public void onHospitalLisDownloadSuccess(List<HospitalModel> list) {
       // Toast.makeText(context, "hospital "+list.size(), Toast.LENGTH_SHORT).show();
        cachedHospitalsList.clear();
        cachedHospitalsList.addAll(list);
        hospitalNameBodyMap.clear();
        for (int i=0;i<list.size();i++){
            hospitalNameBodyMap.put(list.get(i).getName(),list.get(i));
        }


    }

    @Override
    public void onHospitalLisDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onDepartmentLisDownloadSuccess(List<DepartmentModel> list) {
       // Toast.makeText(context, "department "+list.size(), Toast.LENGTH_SHORT).show();
        cachedDeparmentsList.clear();
        cachedDeparmentsList.addAll(list);


    }

    @Override
    public void onDepartmentLisDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


    }


    public void openPatientSignUP(View view) {
        startActivity(new Intent(context,PatientSignupActivity.class));

    }

    public void doctorSignup(View view) {
        startActivity(new Intent(context,DrSignUpActivity.class));

    }
}
