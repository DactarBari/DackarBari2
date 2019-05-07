package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Adapter.PhotoAdapter;
import com.twopibd.dactarbari.doctor.appointment.Adapter.PhotoAdapterOnline;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.LoginResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.PrescriptionInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.ProfileResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.SelectedHospitalsModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.UserInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.photoModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.TYPE_DOCTOR;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;

public class PatientProfileUpdateActivity extends AppCompatActivity implements ApiListener.LoginUserListener,
        ApiListener.profileGet,
        ApiListener.patientProfileUpdate,
        ApiListener.presCriptionUploadListener {
    SessionManager sessionManager;
    String key;


    Context context = this;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_currentProblems)
    EditText ed_currentProblems;


    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;


    UserInfo userInfo;
    @BindView(R.id.ed_email)
    EditText ed_email;

    @BindView(R.id.ed_mobile)
    EditText ed_mobile;

    @BindView(R.id.ed_area)
    EditText ed_area;
    @BindView(R.id.ed_city)
    EditText ed_city;
    @BindView(R.id.ed_postCode)
    EditText ed_postCode;
    @BindView(R.id.ed_streetNo)
    EditText ed_streetNo;
    @BindView(R.id.photo_recyclerview)
    RecyclerView photo_recyclerview;
    @BindView(R.id.ed_houseNo)
    EditText ed_houseNo;
    String user_id;
    String selected_country;
    String gender = "";
    List<PrescriptionInfo> prescriptionInfoList = new ArrayList<>();

    PhotoAdapterOnline mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile_update);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        user_id = sessionManager.getUserId();
        key = sessionManager.getToken();
        MyProgressDialog.with(context);
        Api.getInstance().loginUser(sessionManager.get_userEmail(), sessionManager.get_userPassword(), this);

    }

    public void Update(View view) {
        String USER_TYPE = Constants.TYPE_PATIENT;
        String name = ed_name.getText().toString().trim();

        String gen = gender;
        String password = sessionManager.get_userPassword();
        String mobile = ed_mobile.getText().toString().trim();
        String country = selected_country;
        String house = ed_houseNo.getText().toString().trim();
        String street = ed_streetNo.getText().toString().trim();
        String area = ed_area.getText().toString().trim();
        String city = ed_city.getText().toString().trim();
        String postCode = ed_postCode.getText().toString().trim();
        String problems = ed_currentProblems.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        MyProgressDialog.with(context);
        Api.getInstance().updatePatientProfile(key, USER_TYPE, name, gen, password, mobile, country, house, street, area, city, postCode, problems, email, user_id, this);

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (int i = 0; i < prescriptionInfoList.size(); i++) {

            if (prescriptionInfoList.get(i).getPrescription().contains("/storage/emulated")) {

                File file = new File(prescriptionInfoList.get(i).getPrescription());

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("prescription[]", file.getName(), requestFile);
            }
            try {
                MultipartBody requestBody = builder.build();
                Api.getInstance().AddPrescription(key, user_id, requestBody, this);
            }catch (Exception e){
               // Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }



        }

//        if (prescriptionInfoList.get(i).getPrescription().contains("/storage/emulated")) {
//            builder.setType(MultipartBody.FORM);
//
//
//            File file = new File(prescriptionInfoList.get(i).getPrescription());
//            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            builder.addFormDataPart("prescription[]", file.getName(), requestFile);
//
//        }
//        MultipartBody requestBody = builder.build();
//        Api.getInstance().AddPrescription(key,user_id,requestBody,this);
    }

    @Override
    public void onUserLoginSuccess(String status) {
        Gson gson = new Gson();
        try {
            JSONObject object = new JSONObject(status);
            if (object.getBoolean("status")) {
                LoginResponse response = gson.fromJson(status, LoginResponse.class);
                gender = response.getUserInfo().getGender();
                sessionManager.set_userData(gson.toJson(response.getUserInfo()));
                sessionManager.setToken("Bearer " + response.getAccessToken());
                sessionManager.setuserType(response.getUserInfo().getUserType());
                sessionManager.setLoggedIn(true);
                selected_country = response.getUserInfo().getCountry();
                sessionManager.setuserName(response.getUserInfo().getName());
                sessionManager.setuserId("" + response.getUserInfo().getId());
                sessionManager.set_userPhoto(response.getUserInfo().getPhoto());
                Gson gson1 = new Gson();
                sessionManager.set_userData(gson1.toJson(response.getUserInfo()));
                ed_email.setText(response.getUserInfo().getEmail());
                ed_mobile.setText(response.getUserInfo().getMobile());
                ed_area.setText(response.getUserInfo().getArea());
                ed_city.setText(response.getUserInfo().getCity());
                ed_postCode.setText(response.getUserInfo().getPostcode());
                ed_streetNo.setText(response.getUserInfo().getStreetAddress());
                ed_houseNo.setText(response.getUserInfo().getHouseNo());


                Api.getInstance().getProfile(key, sessionManager.getUserId(), this);

            } else {
                MyProgressDialog.destroy();
                MyDialog.getInstance().with(PatientProfileUpdateActivity.this)
                        .message(object.getString("message"))
                        .autoBack(false)
                        .autoDismiss(false)
                        .show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onUserLoginFailed(String msg) {
        MyProgressDialog.destroy();
        MyDialog.getInstance().with(PatientProfileUpdateActivity.this)
                .message(msg)
                .autoBack(false)
                .autoDismiss(false)
                .show();

    }

    private void initCountrySpinner(List<CountryInfo> countryInfo, String count) {
        // Creating adapter for spinner
        //Toast.makeText(context, count, Toast.LENGTH_SHORT).show();
        List<String> countryList = new ArrayList<>();
        countryList.add("Select");

        for (int i = 0; i < countryInfo.size(); i++) {
            countryList.add(countryInfo.get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(dataAdapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selected_country = "" + countryInfo.get(i).getCode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        for (int j = 0; j < countryInfo.size(); j++) {
            if (count.equals("" + countryInfo.get(j).getCode())) {
                spinnerCountry.setSelection(j);
                selected_country = "" + countryInfo.get(j).getCode();
                break;

            }
        }
    }

    @Override
    public void onProfileSuccess(String list) {
        MyProgressDialog.destroy();
        try {
            JSONObject object = new JSONObject(list.toString());
            if (object.getBoolean("status")) {
                Gson gson = new Gson();
                ProfileResponse response = gson.fromJson(list.toString(), ProfileResponse.class);
                if (response.getDoctorInfo() == null) {
                    if (response.getPatientInfo() != null) {
                        Gson gson1 = new Gson();
                        ProfileResponse response1 = gson1.fromJson(list.toString(), ProfileResponse.class);
                        ed_currentProblems.setText(response1.getPatientInfo().getCurrentProblems());
                        Gson gson2 = new Gson();
                        userInfo = gson2.fromJson(sessionManager.get_userData(), UserInfo.class);
                        //Toast.makeText(context, userInfo.getCountry(), Toast.LENGTH_SHORT).show();
                        initCountrySpinner(response1.getCountryInfo(), userInfo.getCountry());
                        ed_name.setText(sessionManager.getUserName());
                        setUpPrescriptions(response1.getPatientInfo().getPrescriptionInfo());
                    }

                } else {
//                    Gson gson1 = new Gson();
//                    ProfileResponse response1 = gson1.fromJson(list.toString(), ProfileResponse.class);
//                    tv_lastDegree.setText(response1.getDoctorInfo().getQualification());
//                    ed_qualification.setText(response1.getDoctorInfo().getQualification());
//                    tv_name.setText(sessionManager.getUserName());
//                    ed_name.setText(sessionManager.getUserName());
//                    putHospitalMap(response1.getHospitalInfo());
//                    putDepartmentMap(response1.getDepartmentInfo());
//                    putCountrytMap(response1.getCountryInfo());
//                    show_currentHospitalsNames(response1.getDoctorInfo().getCurrentHospitalInfo());
//                    show_DepartmentNames(response1.getDoctorInfo().getDepartment());
//                    Picasso.with(context).load(Constants.PHOTO_BASE + response1.getDoctorInfo().getPhoto()).into(profile_image);
//                    Picasso.with(context).load(Constants.PHOTO_BASE + response1.getDoctorInfo().getPhoto()).into(bigImage);
//                    init_department_spinner(response1.getDepartmentInfo(), response1.getDoctorInfo().getDepartment());
//                    initCurrentHospitals(response1.getHospitalInfo(), response1.getDoctorInfo().getCurrentHospitalInfo());
//                    initPreviousHospitals(response1.getDoctorInfo().getPreviousHospitalInfo());
//                    innitTypeSpinner(response1.getDoctorInfo().getType());
//                    Gson gson2 = new Gson();
//                    userInfo = gson2.fromJson(sessionManager.get_userData(), UserInfo.class);
//                    initCountrySpinner(response1.getCountryInfo(), userInfo.getCountry());
//                    initBasicFields(userInfo);


                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void setUpPrescriptions(List<PrescriptionInfo> prescriptionInfo) {
        // Toast.makeText(context, ""+prescriptionInfo.size(), Toast.LENGTH_SHORT).show();
        prescriptionInfoList.addAll(prescriptionInfo);


        mAdapter = new PhotoAdapterOnline(prescriptionInfoList);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        photo_recyclerview.setLayoutManager(staggeredGridLayoutManager);
        photo_recyclerview.setItemAnimator(new DefaultItemAnimator());
        photo_recyclerview.setAdapter(mAdapter);


    }


    private void showChooser() {

        Pix.start(PatientProfileUpdateActivity.this, 99, 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 99) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            for (int i = 0; i < returnValue.size(); i++) {
                prescriptionInfoList.add(new PrescriptionInfo(0, 0, returnValue.get(i), "", ""));
            }
            mAdapter.notifyDataSetChanged();


        }
    }

    @Override
    public void onProfileFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onpatientProfileUpdateSuccess(StatusMessage list) {
        Gson gson = new Gson();

        Toast.makeText(context, list.getMessage(), Toast.LENGTH_SHORT).show();

        MyProgressDialog.destroy();
        onBackPressed();
    }

    @Override
    public void onpatientProfileUpdateFailed(String msg) {
        MyProgressDialog.destroy();

        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    public void addPhoto(View view) {
        Dexter.withActivity(PatientProfileUpdateActivity.this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            showChooser();


                        } else {
                            Toast.makeText(PatientProfileUpdateActivity.this, "Permissions are not granted!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    @Override
    public void onPrescriptionAddSuccess(StatusMessage list) {
        if (list.getStatus()) {
            Toast.makeText(context, list.getMessage(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, list.getMessage(), Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onPrescriptionAddFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


    }
}
