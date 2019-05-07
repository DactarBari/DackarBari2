package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.TypedValue;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.twopibd.dactarbari.doctor.appointment.Adapter.SelectedHospitalsAdapter;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Model.CountryInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.CurrentHospitalInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.Model.LoginResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.PreviousHospitalInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.ProfileResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.SelectedHospitalsModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.UserInfo;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.TYPE_DOCTOR;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.hospitalNameBodyMap;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.hospital_id_Body_map;


public class DrPersonalInfoActivity extends AppCompatActivity implements ApiListener.profileGet,
        SelectedHospitalsAdapter.HospitalClickListener,
        ApiListener.LoginUserListener ,ApiListener.drProfileUpdate{
    SessionManager sessionManager;
    String key;
    @BindView(R.id.tv_lastDegree)
    TextView tv_lastDegree;
    @BindView(R.id.tv_name)
    TextView tv_name;

    @BindView(R.id.tv_hospitals)
    TextView tv_hospitals;
    @BindView(R.id.tv_department)
    TextView tv_department;
    @BindView(R.id.tv_country)
    TextView tv_country;
    @BindView(R.id.bigImage)
    ImageView bigImage;

    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    Context context = this;
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_qualification)
    EditText ed_qualification;
    @BindView(R.id.spinnerDepartment)
    Spinner spinnerDepartment;
    @BindView(R.id.spinnerType)
    Spinner spinnerType;
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.autoCompleteCurrentHospital)
    AutoCompleteTextView autoCompleteCurrentHospital;
    @BindView(R.id.autoCompletePrevHospital)
    AutoCompleteTextView autoCompletePrevHospital;
    @BindView(R.id.recycler_view_currentHospital)
    RecyclerView recycler_view_currentHospital;
    @BindView(R.id.recycler_viewPrevious)
    RecyclerView recycler_viewPrevious;
    List<SelectedHospitalsModel> SelectedlistCurrent = new ArrayList<>();
    List<SelectedHospitalsModel> SelectedlistPrevious = new ArrayList<>();
    List<String> hospitalsNameOnly = new ArrayList<>();
    String selected_department = "";
    String selected_type = "";
    UserInfo userInfo;
    String selectedCountry;
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
    @BindView(R.id.ed_houseNo)
    EditText ed_houseNo;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_personal_info);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        setTitle(sessionManager.getUserName());

        key = sessionManager.getToken();
        user_id=sessionManager.getUserId();
        MyProgressDialog.with(context);
        // Api.getInstance().getProfile(key,sessionManager.getUserId(), this);
        Api.getInstance().loginUser(sessionManager.get_userEmail(), sessionManager.get_userPassword(), this);


    }


    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
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


                    }

                } else {
                    Gson gson1 = new Gson();
                    ProfileResponse response1 = gson1.fromJson(list.toString(), ProfileResponse.class);
                    tv_lastDegree.setText(response1.getDoctorInfo().getQualification());
                    ed_qualification.setText(response1.getDoctorInfo().getQualification());
                    tv_name.setText(sessionManager.getUserName());
                    ed_name.setText(sessionManager.getUserName());
                    putHospitalMap(response1.getHospitalInfo());
                    putDepartmentMap(response1.getDepartmentInfo());
                    putCountrytMap(response1.getCountryInfo());
                    show_currentHospitalsNames(response1.getDoctorInfo().getCurrentHospitalInfo());
                    show_DepartmentNames(response1.getDoctorInfo().getDepartment());
                    Picasso.with(context).load(Constants.PHOTO_BASE + response1.getDoctorInfo().getPhoto()).into(profile_image);
                    Picasso.with(context).load(Constants.PHOTO_BASE + response1.getDoctorInfo().getPhoto()).into(bigImage);
                    init_department_spinner(response1.getDepartmentInfo(), response1.getDoctorInfo().getDepartment());
                    initCurrentHospitals(response1.getHospitalInfo(), response1.getDoctorInfo().getCurrentHospitalInfo());
                    initPreviousHospitals(response1.getDoctorInfo().getPreviousHospitalInfo());
                    innitTypeSpinner(response1.getDoctorInfo().getType());
                    Gson gson2 = new Gson();
                    userInfo = gson2.fromJson(sessionManager.get_userData(), UserInfo.class);
                    initCountrySpinner(response1.getCountryInfo(), userInfo.getCountry());
                    initBasicFields(userInfo);


                }


            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    private void initBasicFields(UserInfo userInfo) {
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
                    selectedCountry = ""+countryInfo.get(i).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        for (int j = 0; j < countryInfo.size(); j++) {
            if (count.equals("" + countryInfo.get(j).getId())) {
                spinnerCountry.setSelection(j);
                break;

            }
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,HomeActivityDoctor.class));
        finish();
    }

    private void innitTypeSpinner(Integer type) {
        int selectedPos = type;
        List<String> types = new ArrayList<>();
        types.add("General");
        types.add("Specialist");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, types);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);
        spinnerType.setSelection(selectedPos - 1);
        selected_type = "" + type;
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_type = "" + (i + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initPreviousHospitals(List<PreviousHospitalInfo> previousHospitalInfo) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, hospitalsNameOnly);
        autoCompletePrevHospital.setAdapter(adapter);
        for (int i = 0; i < previousHospitalInfo.size(); i++) {
            SelectedlistPrevious.add(new SelectedHospitalsModel(hospital_id_Body_map.get(previousHospitalInfo.get(i).getHospitalId()), false));

        }
        SelectedHospitalsAdapter mAdapter = new SelectedHospitalsAdapter(context, SelectedlistPrevious, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_viewPrevious.setLayoutManager(staggeredGridLayoutManager);
        recycler_viewPrevious.setItemAnimator(new DefaultItemAnimator());
        recycler_viewPrevious.setAdapter(mAdapter);
        autoCompletePrevHospital.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                SelectedlistPrevious.add(new SelectedHospitalsModel(hospitalNameBodyMap.get(selection), true));
                mAdapter.notifyItemInserted(SelectedlistCurrent.size() - 1);
                autoCompletePrevHospital.setText("");


            }
        });
        autoCompletePrevHospital.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    autoCompletePrevHospital.showDropDown();

            }
        });

        autoCompletePrevHospital.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                autoCompletePrevHospital.showDropDown();
                return false;
            }
        });

    }

    private void initCurrentHospitals(List<HospitalModel> hospitalInfo, List<CurrentHospitalInfo> currentHospitalInfo) {
        hospitalNameBodyMap.clear();
        hospital_id_Body_map.clear();
        for (int i = 0; i < hospitalInfo.size(); i++) {
            hospitalNameBodyMap.put(hospitalInfo.get(i).getName(), hospitalInfo.get(i));
            hospital_id_Body_map.put(hospitalInfo.get(i).getId(), hospitalInfo.get(i));
        }
        for (int i = 0; i < hospitalInfo.size(); i++) {
            //  SelectedlistCurrent.add(new SelectedHospitalsModel(Data.cachedHospitalsList.get(i), false));
            hospitalsNameOnly.add(hospitalInfo.get(i).getName());
        }
        for (int i = 0; i < currentHospitalInfo.size(); i++) {
            SelectedlistCurrent.add(new SelectedHospitalsModel(hospital_id_Body_map.get(currentHospitalInfo.get(i).getHospitalId()), false));

        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, hospitalsNameOnly);
        autoCompleteCurrentHospital.setAdapter(adapter);

        SelectedHospitalsAdapter mAdapterCurrentHospital = new SelectedHospitalsAdapter(context, SelectedlistCurrent, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_view_currentHospital.setLayoutManager(staggeredGridLayoutManager);
        recycler_view_currentHospital.setItemAnimator(new DefaultItemAnimator());
        recycler_view_currentHospital.setAdapter(mAdapterCurrentHospital);


        autoCompleteCurrentHospital.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                SelectedlistCurrent.add(new SelectedHospitalsModel(hospitalNameBodyMap.get(selection), true));
                mAdapterCurrentHospital.notifyItemInserted(SelectedlistCurrent.size() - 1);
                autoCompleteCurrentHospital.setText("");


            }
        });
        autoCompleteCurrentHospital.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    autoCompleteCurrentHospital.showDropDown();

            }
        });

        autoCompleteCurrentHospital.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                autoCompleteCurrentHospital.showDropDown();
                return false;
            }
        });

    }


    private void init_department_spinner(List<DepartmentInfo> departmentInfo, Integer department) {
        int selectedPos = 0;
        List<String> departments = new ArrayList<>();
        for (int i = 0; i < departmentInfo.size(); i++) {
            departments.add(departmentInfo.get(i).getName());
            if (departmentInfo.get(i).getId() == department) {
                selectedPos = i;

            }
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, departments);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(dataAdapter);
        spinnerDepartment.setSelection(selectedPos);
        selected_department = "" + departmentInfo.get(selectedPos).getId();
        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected_department = "" + departmentInfo.get(i).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    private void putCountrytMap(List<CountryInfo> countryInfo) {
        for (CountryInfo c : countryInfo) {
            // Data.putDepartmentlMap(c.getId(),c);
        }
    }

    private void show_DepartmentNames(Integer department) {
        tv_department.setText("Expert in : " + Data.getDepartmentNameByID(department));
    }

    private void putDepartmentMap(List<DepartmentInfo> departmentInfo) {
        for (DepartmentInfo d : departmentInfo) {
            Data.putDepartmentlMap(d.getId(), d.getName());
        }
    }

    private void putHospitalMap(List<HospitalModel> hospitalInfo) {
        for (HospitalModel h : hospitalInfo) {
            Data.putHospitalMap(h.getId(), h.getName());
        }

    }

    private void show_currentHospitalsNames(List<CurrentHospitalInfo> currentHospitalInfo) {
        String hospitals = "";

        for (CurrentHospitalInfo h : currentHospitalInfo) {
            hospitals += Data.getHospitalNameByID(h.getHospitalId()) + "\n";

        }
        tv_hospitals.setText(hospitals);
    }

    @Override
    public void onProfileFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onHospitalSelected(SelectedHospitalsModel data) {

    }

    public void Update(View view) {

        List<String> prev = new ArrayList<>();
        for (SelectedHospitalsModel model : SelectedlistPrevious) {
            prev.add("" + model.getHospitalModel().getId());
        }
        String previous_hospital = prev.toString();
        List<String> current = new ArrayList<>();
        for (SelectedHospitalsModel model : SelectedlistCurrent) {
            current.add("" + model.getHospitalModel().getId());
        }
        String current_hospital = current.toString();
        String qualification = ed_qualification.getText().toString().trim();
        String password=sessionManager.get_userPassword();
        MyProgressDialog.with(context);
        Api.getInstance().DoctorProfileUpdate(key,
                TYPE_DOCTOR,
                ed_name.getText().toString().trim(),
                "male",
                ed_email.getText().toString().trim(),
                password,

                ed_mobile.getText().toString().trim(),
                selectedCountry,
                ed_houseNo.getText().toString().trim(),
                ed_streetNo.getText().toString().trim(),
                ed_area.getText().toString().trim(),
                ed_city.getText().toString().trim(),
                ed_postCode.getText().toString().trim(),
                selected_type,
                ed_qualification.getText().toString().trim(),
                current_hospital,
                selected_department,
                previous_hospital,
                user_id,
                this


                );

    }

    @Override
    public void onUserLoginSuccess(String status) {
        Gson gson = new Gson();
        try {
            JSONObject object = new JSONObject(status);
            if (object.getBoolean("status")) {
                LoginResponse response = gson.fromJson(status, LoginResponse.class);

                sessionManager.set_userData(gson.toJson(response.getUserInfo()));
                sessionManager.setToken("Bearer " + response.getAccessToken());
                sessionManager.setuserType(response.getUserInfo().getUserType());
                sessionManager.setLoggedIn(true);
                sessionManager.setuserName(response.getUserInfo().getName());
                sessionManager.setuserId("" + response.getUserInfo().getId());
                Gson gson1=new Gson();
                sessionManager.set_userData(gson1.toJson(response.getUserInfo()));
                sessionManager.set_userPhoto(response.getUserInfo().getPhoto());
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
                MyDialog.getInstance().with(DrPersonalInfoActivity.this)
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
        MyDialog.getInstance().with(DrPersonalInfoActivity.this)
                .message(msg)
                .autoBack(false)
                .autoDismiss(false)
                .show();

    }

    @Override
    public void ondrProfileUpdateSuccess(StatusMessage list) {
        MyProgressDialog.destroy();
        Toast.makeText(context, list.getMessage(), Toast.LENGTH_SHORT).show();
        onBackPressed();
    }

    @Override
    public void ondrProfileUpdateFailed(String msg) {
        MyProgressDialog.destroy();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();



    }
}
