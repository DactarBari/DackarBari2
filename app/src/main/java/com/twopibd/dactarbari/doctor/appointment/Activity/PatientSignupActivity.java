package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.fxn.pix.Pix;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.twopibd.dactarbari.doctor.appointment.Adapter.PhotoAdapter;
import com.twopibd.dactarbari.doctor.appointment.Adapter.SelectedHospitalsAdapter;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.photoModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;

public class PatientSignupActivity extends AppCompatActivity implements ApiListener.patientSignUpListener {
    @BindView(R.id.spinnerCountry)
    Spinner spinnerCountry;
    @BindView(R.id.photo_recyclerview)
    RecyclerView photo_recyclerview;
    @BindView(R.id.spinnerGender)
    Spinner spinnerGender;
    Context context = this;
    @BindView(R.id.ed_name)
    EditText ed_name;
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
    @BindView(R.id.ed_currentProblems)
    EditText ed_currentProblems;


    ProgressDialog progressDialog;
    String selectedGender, selectedCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_signup);
        photoModelList.clear();
        ButterKnife.bind(this);
        initCountrySpinner();
        initGenderSpinner();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        setEmailUniqueListener();
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
                                        MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message(response.getMessage()).show();
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
                        MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("Enter Correct Email address").show();
                        ed_email.setError("Enter Correct Email address");

                    }
                }
            }
        });
    }

    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
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

    private void initCountrySpinner() {
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
                    Toast.makeText(context, selectedCountry, Toast.LENGTH_SHORT).show();
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

    public void SignUpPatient(View view) {
        progressDialog.show();
        String TYPE = "patient";
        String name = ed_name.getText().toString().trim();
        String gender = selectedGender;


        String area = ed_area.getText().toString().trim();
        String mobile = ed_mobile.getText().toString().trim();
        String password = ed_password.getText().toString().trim();
        String confirmPass = ed_confirmPass.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String house = ed_house.getText().toString().trim();
        String street = ed_street.getText().toString().trim();
        String city = ed_city.getText().toString().trim();
        String postCode = ed_postCode.getText().toString().trim();
        String problems = ed_currentProblems.getText().toString().trim();

        if (photoModelList.size() > 0) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);


            for (int j = 0; j < photoModelList.size(); j++) {
                File file = new File(photoModelList.get(j).getPhotoLink());

                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                builder.addFormDataPart("prescription[]", file.getName(), requestFile);
            }

            MultipartBody requestBody = builder.build();


            if (!name.isEmpty()) {
                if (!gender.isEmpty()) {
                    if (!gender.isEmpty()) {
                        if (!mobile.isEmpty()) {
                            if (!email.isEmpty()) {
                                if (!password.isEmpty()) {
                                    if (!house.isEmpty()) {
                                        if (!street.isEmpty()) {
                                            if (!area.isEmpty()) {
                                                if (!city.isEmpty()) {
                                                    if (!postCode.isEmpty()) {
                                                        if (!selectedCountry.isEmpty()) {
                                                            try {
//                                                                Api.getInstance().signUpPatient(c_m_b(TYPE_DOCTOR),
////                                                                        c_m_b(name),
////                                                                        c_m_b(gender),
////                                                                        c_m_b(email),
////                                                                        c_m_b(password),
////                                                                        c_m_b(mobile),
////                                                                        c_m_b(selectedCountry),
////                                                                        c_m_b(house),
////                                                                        c_m_b(street),
////                                                                        c_m_b(area),
////                                                                        c_m_b(city),
////                                                                        c_m_b(postCode),
////                                                                        c_m_b(problems),
////                                                                        requestBody,
////                                                                        this);
                                                                Api.getInstance().signUpPatient(TYPE,
                                                                        name,
                                                                        gender,
                                                                        email,
                                                                        password,
                                                                        mobile,
                                                                        selectedCountry,
                                                                        house,
                                                                        street,
                                                                        area,
                                                                        city,
                                                                        postCode,
                                                                        problems,
                                                                        requestBody,
                                                                        this);
                                                            } catch (Exception e) {
                                                                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                                            }


                                                        } else {
                                                            MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("department").show();

                                                        }
                                                    } else {
                                                        MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("current hospital").show();

                                                    }
                                                } else {
                                                    MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("previous hospital").show();

                                                }
                                            } else {
                                                MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("qualification").show();

                                            }
                                        } else {
                                            MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("type").show();

                                        }
                                    } else {
                                        MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("country").show();

                                    }
                                } else {
                                    MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("postcode").show();

                                }
                            } else {
                                MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("city").show();

                            }
                        } else {
                            MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("area").show();

                        }
                    } else {
                        MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("street").show();

                    }
                } else {
                    MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("house").show();

                }
            } else {
                MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("password").show();

            }
        } else {
            MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message("No photo").show();

        }

    }


    private RequestBody c_m_b(String aThis) {
        return
                RequestBody.create(
                        MediaType.parse("multipart/form-data"), aThis);
    }

    public void addPhoto(View view) {
        Dexter.withActivity(PatientSignupActivity.this).withPermissions(Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {

                            showChooser();



                        } else {
                            Toast.makeText(PatientSignupActivity.this, "Permissions are not granted!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();

    }

    private void showChooser() {

        Pix.start(PatientSignupActivity.this, 99, 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && requestCode == 99) {
            ArrayList<String> returnValue = data.getStringArrayListExtra(Pix.IMAGE_RESULTS);
            for (int i = 0; i < returnValue.size(); i++) {
                photoModelList.add(new photoModel(returnValue.get(i)));
            }
            try {
                PhotoAdapter mAdapter = new PhotoAdapter();
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                photo_recyclerview.setLayoutManager(staggeredGridLayoutManager);
                photo_recyclerview.setItemAnimator(new DefaultItemAnimator());
                photo_recyclerview.setAdapter(mAdapter);
            } catch (Exception e) {
                Toast.makeText(context, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onPatientSignUpPostSuccess(StatusMessage data) {
        Gson gson=new Gson();
        Toast.makeText(context, gson.toJson(data), Toast.LENGTH_LONG).show();


        progressDialog.dismiss();
        try {
            MyDialog.getInstance().with(PatientSignupActivity.this).autoBack(false).autoDismiss(false).message(gson.toJson(data)).show();

        } catch (Exception e) {
           // Toast.makeText(context,"Error from here" , Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onPatientSignUpPostFailed(String msg) {
        progressDialog.dismiss();
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }


}
