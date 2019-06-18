package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.twopibd.dactarbari.doctor.appointment.Adapter.SelectedDegreeAdapter;
import com.twopibd.dactarbari.doctor.appointment.Adapter.SelectedHospitalsAdapter;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.QualificationModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SelectedHospitalsModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SelectedQualificationModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.Test;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.hospitalNameBodyMap;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;

public class DrSignUpActivity extends AppCompatActivity implements SelectedHospitalsAdapter.HospitalClickListener,
        ApiListener.drBasicInfoPostListener,
        ApiListener.StringRequestListener, SelectedDegreeAdapter.DegreeClickListener {
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.spinnerTitle)
    Spinner spinnerTitle;
    @BindView(R.id.autoCompletePrevHospital)
    AutoCompleteTextView autoCompletePrevHospital;
    @BindView(R.id.autoCompleteQualification)
    AutoCompleteTextView autoCompleteQualification;
    @BindView(R.id.autoCompleteCurrentHospital)
    AutoCompleteTextView autoCompleteCurrentHospital;
    @BindView(R.id.spinnerType)
    Spinner spinnerType;
    @BindView(R.id.spinnerDepartment)
    Spinner spinnerDepartment;
    @BindView(R.id.spinnerGender)
    Spinner spinnerGender;
    Context context = this;
    @BindView(R.id.image)
    ImageView image;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_viewQualification)
    RecyclerView recycler_viewQualification;
    @BindView(R.id.recycler_view_currentHospital)
    RecyclerView recycler_view_currentHospital;
    SelectedHospitalsAdapter mAdapter;
    SelectedDegreeAdapter DegreemAdapter;
    SelectedHospitalsAdapter mAdapterCurrentHospital;
    List<SelectedHospitalsModel> Selectedlist = new ArrayList<>();
    List<SelectedHospitalsModel> SelectedlistCurrent = new ArrayList<>();
    public static List<SelectedQualificationModel> SelectedQualifications = new ArrayList<>();
    @BindView(R.id.ed_firstName)
    EditText ed_firstName;
    @BindView(R.id.ed_mobile)
    EditText ed_mobile;
    @BindView(R.id.ed_password)
    EditText ed_password;
    @BindView(R.id.ed_confirmPass)
    EditText ed_confirmPass;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_house)
    EditText ed_house;
    @BindView(R.id.ed_street)
    EditText ed_street;
    @BindView(R.id.ed_city)
    EditText ed_city;
    @BindView(R.id.ed_area)
    EditText ed_area;
    @BindView(R.id.ed_postCode)
    EditText ed_postCode;
    @BindView(R.id.ed_last_name)
    EditText ed_last_name;
    @BindView(R.id.ed_confirm_email)
    EditText ed_confirm_email;

    Uri resultUri;
    String selectedCountry = "";
    String selectedGender = "";
    String selectedType = "";
    String selectedDepartment = "";
    String selectedTitle = "";
    ProgressDialog progressDialog;
    String QualificationString="";
    String device_ID=null;

    public static List<QualificationModel> MEDICAL_DEGREE = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_sign_up);
        ButterKnife.bind(this);
        photoModelList.clear();
        initCountrySpinner();
        initTypeSpinner();
        initDepartmentSpinner();
        initPreviousSelectedHospitals();
        initCurrentSelectedHospitals();
        initQualification();
        initGenderSpinner();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        setEmailUniqueListener();
        init_title_spinner();
        device_ID = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    private void initQualification() {
        MEDICAL_DEGREE.add(new QualificationModel("1", "MBBS"));
        MEDICAL_DEGREE.add(new QualificationModel("2", "BDS"));
        MEDICAL_DEGREE.add(new QualificationModel("3", "BAMS"));
        MEDICAL_DEGREE.add(new QualificationModel("4", "BUMS"));
        MEDICAL_DEGREE.add(new QualificationModel("5", "BHMS"));

        List<String> Qualifications = new ArrayList<>();
        for (int i = 0; i < MEDICAL_DEGREE.size(); i++) {
            //  Selectedlist.add(new SelectedHospitalsModel(Data.cachedHospitalsList.get(i), false));
            Qualifications.add(MEDICAL_DEGREE.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, Qualifications);
        autoCompleteQualification.setAdapter(adapter);
        setAutoCompleteAdapterQualification();

        DegreemAdapter = new SelectedDegreeAdapter(context, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recycler_viewQualification.setLayoutManager(staggeredGridLayoutManager);
        recycler_viewQualification.setItemAnimator(new DefaultItemAnimator());
        recycler_viewQualification.setAdapter(DegreemAdapter);


    }

    private void setAutoCompleteAdapterQualification() {
        autoCompleteQualification.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                SelectedQualifications.add(new SelectedQualificationModel(true, MEDICAL_DEGREE.get(position)));
                DegreemAdapter.notifyItemInserted(SelectedQualifications.size() - 1);
                autoCompleteQualification.setText("");
                QualificationString+=MEDICAL_DEGREE.get(position)+",";


            }
        });
        autoCompleteQualification.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    autoCompleteQualification.showDropDown();

            }
        });

        autoCompleteQualification.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                autoCompleteQualification.showDropDown();
                return false;
            }
        });
    }

    private void init_title_spinner() {
        List<String> title = new ArrayList<>();
        title.add("Select");
        title.add("Mr");
        title.add("Mrs");
        title.add("Dr");
        title.add("Other");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, title);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTitle.setAdapter(dataAdapter);
        spinnerTitle.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedTitle = title.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void initGenderSpinner() {
        List<String> gender = new ArrayList<>();
        gender.add("Select");
        gender.add("Male");
        gender.add("Female");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGender.setAdapter(dataAdapter);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedGender = gender.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initPreviousSelectedHospitals() {
        List<String> hospitals = new ArrayList<>();
        for (int i = 0; i < Data.cachedHospitalsList.size(); i++) {
            //  Selectedlist.add(new SelectedHospitalsModel(Data.cachedHospitalsList.get(i), false));
            hospitals.add(Data.cachedHospitalsList.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, hospitals);
        autoCompletePrevHospital.setAdapter(adapter);
        setAutoCompleteAdapter();

        mAdapter = new SelectedHospitalsAdapter(context, Selectedlist, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(staggeredGridLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);
    }

    private void initCurrentSelectedHospitals() {

        List<String> hospitals = new ArrayList<>();
        for (int i = 0; i < Data.cachedHospitalsList.size(); i++) {
            //  SelectedlistCurrent.add(new SelectedHospitalsModel(Data.cachedHospitalsList.get(i), false));
            hospitals.add(Data.cachedHospitalsList.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, hospitals);
        autoCompleteCurrentHospital.setAdapter(adapter);
        setAutoCompleteAdapterCurrent();

        mAdapterCurrentHospital = new SelectedHospitalsAdapter(context, SelectedlistCurrent, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler_view_currentHospital.setLayoutManager(staggeredGridLayoutManager);
        recycler_view_currentHospital.setItemAnimator(new DefaultItemAnimator());
        recycler_view_currentHospital.setAdapter(mAdapterCurrentHospital);
    }

    private void setAutoCompleteAdapter() {
        autoCompletePrevHospital.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                Selectedlist.add(new SelectedHospitalsModel(hospitalNameBodyMap.get(selection), true));
                mAdapter.notifyItemInserted(Selectedlist.size() - 1);
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

    private void setAutoCompleteAdapterCurrent() {
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

    private void initCountrySpinner() {
        // Creating adapter for spinner
        List<String> countryList = new ArrayList<>();
        countryList.add("Select");

        for (int i = 0; i < Data.cachedCountryList.size(); i++) {
            countryList.add(Data.cachedCountryList.get(i).getName());
        }
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countryList);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountry.setAdapter(dataAdapter);
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedCountry = Data.cachedCountryList.get(i - 1).getCode();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initTypeSpinner() {
        // Creating adapter for spinner
        List<String> type = new ArrayList<>();
        type.add("Select");
        type.add("General");
        type.add("Specialist");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, type);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerType.setAdapter(dataAdapter);
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedType = "" + i;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void initDepartmentSpinner() {
        // Creating adapter for spinner
        List<String> department = new ArrayList<>();
        department.add("Select");
        for (int i = 0; i < Data.cachedDeparmentsList.size(); i++) {
            department.add(Data.cachedDeparmentsList.get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, department);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDepartment.setAdapter(dataAdapter);
        spinnerDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i > 0) {
                    selectedDepartment = "" + Data.cachedDeparmentsList.get(i - 1).getId();
                    // Toast.makeText(context, selectedDepartment, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void PickImage(View view) {
        askPermission();

    }

    private void askPermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            // do you work now
                            openCamera();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // permission is denied permenantly, navigate user to app settings
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();
    }

    private void openCamera() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)

                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                image.setImageURI(resultUri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public void onHospitalSelected(SelectedHospitalsModel data) {
    }

    public void SignUpDr(View view) {


        String TYPE_DOCTOR = "doctor";
        String name = selectedTitle + " " + ed_firstName.getText().toString().trim() + " " + ed_last_name.getText().toString().trim();
        String gender = selectedGender;
        String country = selectedCountry;
        String type = selectedType;

        List<String> Cids = new ArrayList<>();
        List<String> Pids = new ArrayList<>();
        //current
        for (int i = 0; i < SelectedlistCurrent.size(); i++) {
            Cids.add("" + SelectedlistCurrent.get(i).getHospitalModel().getId());
        }
        String current_hospital = new Gson().toJson(Cids);


        for (int i = 0; i < Selectedlist.size(); i++) {
            Pids.add("" + Selectedlist.get(i).getHospitalModel().getId());
        }
        String previous_hospital = new Gson().toJson(Pids);

        String department = selectedDepartment;
        String area = ed_area.getText().toString().trim();
        String mobile = ed_mobile.getText().toString().trim();
        String password = ed_password.getText().toString().trim();
        String confirmPass = ed_confirmPass.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String house = ed_house.getText().toString().trim();
        String street = ed_street.getText().toString().trim();
        String city = ed_city.getText().toString().trim();
        String postCode = ed_postCode.getText().toString().trim();
        String qualification = QualificationString;


        if (resultUri != null) {
            File f = new File(resultUri.getPath());
            MultipartBody.Part photo = null;
            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), f);
            photo = MultipartBody.Part.createFormData("photo", f.getName(), requestFile);
            if (!name.isEmpty()) {
                if (!gender.isEmpty()) {
                    if (!gender.isEmpty()) {
                        if (!mobile.isEmpty()) {
                            if (isBothEmailValid()) {
                                if (!password.isEmpty()) {
                                    if (!house.isEmpty()) {
                                        if (!street.isEmpty()) {
                                            if (!area.isEmpty()) {
                                                if (!city.isEmpty()) {
                                                    if (!postCode.isEmpty()) {
                                                        if (!country.isEmpty()) {
                                                            if (!type.isEmpty()) {
                                                                if (!qualification.isEmpty()) {
                                                                    if (!previous_hospital.isEmpty()) {
                                                                        if (!current_hospital.isEmpty()) {
                                                                            if (!department.isEmpty()) {
                                                                                progressDialog.show();
                                                                                Api.getInstance().signUpDoctor(c_m_b(TYPE_DOCTOR),
                                                                                        c_m_b(name),
                                                                                        c_m_b(gender),
                                                                                        c_m_b(email),
                                                                                        c_m_b(password),
                                                                                        c_m_b(mobile),
                                                                                        c_m_b(country),
                                                                                        c_m_b(house),
                                                                                        c_m_b(street),
                                                                                        c_m_b(area),
                                                                                        c_m_b(city),
                                                                                        c_m_b(postCode),
                                                                                        c_m_b(type),
                                                                                        c_m_b(qualification),
                                                                                        c_m_b(current_hospital),
                                                                                        c_m_b(department),
                                                                                        c_m_b(previous_hospital),

                                                                                        photo,
                                                                                        this);

                                                                            } else {
                                                                                MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select a depertment").show();

                                                                            }
                                                                        } else {
                                                                            MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your current hospital").show();

                                                                        }
                                                                    } else {
                                                                        MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your previous hospital").show();

                                                                    }
                                                                } else {
                                                                    MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your qualification").show();

                                                                }
                                                            } else {
                                                                MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your type").show();

                                                            }
                                                        } else {
                                                            MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your country").show();

                                                        }
                                                    } else {
                                                        MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your postcode").show();

                                                    }
                                                } else {
                                                    MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your city").show();

                                                }
                                            } else {
                                                MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your area").show();

                                            }
                                        } else {
                                            MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your street").show();

                                        }
                                    } else {
                                        MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your house number").show();

                                    }
                                } else {
                                    MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Set a password").show();

                                }
                            } else {
                                MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Please provide  your correct Email in both field").show();

                            }
                        } else {
                            MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Enter your Mobile").show();

                        }
                    }
                } else {
                    MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your Gender").show();

                }
            } else {
                MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Enter your name").show();

            }
        } else {
            MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Select your Photo").show();

        }


    }

    private boolean isBothEmailValid() {
        boolean status=false;
        String firstEmail=ed_email.getText().toString().trim();
        String secondEmail=ed_confirm_email.getText().toString().trim();
        if (firstEmail!=null && secondEmail!=null && isValidEmail(firstEmail)&&isValidEmail(secondEmail))
            status=true;
        else status=false;
        return status;
    }

    private void setEmailUniqueListener() {
        ed_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ed_email.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        ed_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    String email = ed_email.getText().toString().trim();
                    if (isValidEmail(email)) {
                        Api.getInstance().isEmailUnique(email, new ApiListener.emailCheckListener() {
                            @Override
                            public void onEmailCheckSuccess(StatusMessage response) {
                                try {
                                    if (response.getStatus()) {
                                        // Toast.makeText(context, "Email Ok", Toast.LENGTH_SHORT).show();
                                    } else {
                                        MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message(response.getMessage()).show();
                                        ed_email.setError(response.getMessage());

                                    }

                                } catch (Exception e) {
                                    Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();


                                }

                            }

                            @Override
                            public void onEmailCheckFailed(String msg) {
                                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                            }
                        });

                    } else {
                        MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(false).autoDismiss(false).message("Enter Correct Email address").show();
                        ed_email.setError("Enter Correct Email address");

                    }
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private RequestBody c_m_b(String aThis) {
        return
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), aThis);
    }

    @Override
    public void onBasicInfoPostSuccess(StatusMessage data) {
        progressDialog.dismiss();
        //Toast.makeText(context, data.getMessage(), Toast.LENGTH_LONG).show();
        //Gson gson = new Gson();
        try {
            MyDialog.getInstance().with(DrSignUpActivity.this).autoBack(true).autoDismiss(false).message(data.getMessage()).show();

        } catch (Exception e) {
            Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBasicInfoPostFailed(String msg) {
        progressDialog.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStringPostSuccess(String data) {
        Toast.makeText(context, data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStringPostFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void back(View view) {
        onBackPressed();
    }

    @Override
    public void onDegreeSelected(SelectedQualificationModel data) {
        Toast.makeText(context, data.getQualificationModel().getName(), Toast.LENGTH_SHORT).show();
    }
}
