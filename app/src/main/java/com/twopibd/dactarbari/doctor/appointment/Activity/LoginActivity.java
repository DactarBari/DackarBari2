package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.LoginResponse;
import com.twopibd.dactarbari.doctor.appointment.Model.TestModel;
import com.twopibd.dactarbari.doctor.appointment.Model.testSelectedModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements ApiListener.LoginUserListener, ApiListener.testNamesDownloadListener {
    @BindView(R.id.ed_phone)
    EditText ed_phone;
    @BindView(R.id.ed_password)
    EditText ed_password;
    String phone, password;
    ProgressDialog progressDialog;
    SessionManager sessionManager;
    String DOCTOR = "d";
    String PATIENT = "p";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sessionManager = new SessionManager(this);
        ButterKnife.bind(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait");
        Toast.makeText(this, "zz3 Mukul", Toast.LENGTH_SHORT).show();







        if(sessionManager.getLoggedIn()){
            if (sessionManager.getUserType().equals(Constants.TYPE_DOCTOR)){
                startActivity(new Intent(this,HomeActivityDoctor.class));
                finish();
            }else  if (sessionManager.getUserType().equals(Constants.TYPE_PATIENT)){
                startActivity(new Intent(this,PatientNewActivity.class));
                finish();
            }else  if (sessionManager.getUserType().equals(Constants.TYPE_ASSISTANT)){
                startActivity(new Intent(this,AssistantHomeActivity.class));
                finish();
            }else {
                Toast.makeText(this, "Unknown user type.Please login again", Toast.LENGTH_SHORT).show();

            }
        }
    }

    public void openSignUpActivity(View view) {
        startActivity(new Intent(this, SignUpTypeActivity.class));
    }

    public void login(View view) {
        phone = ed_phone.getText().toString().trim();
        password = ed_password.getText().toString().trim();

        if (phone.length() > 0 && password.length() > 0) {
            progressDialog.show();
            Api.getInstance().loginUser(phone, password, this);
            //startActivity(new Intent(this,HomeActivityDoctor.class));

        }


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

    @Override
    public void onUserLoginSuccess(String status) {
        progressDialog.dismiss();
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
                sessionManager.setuserId(""+response.getUserInfo().getId());
                sessionManager.set_userPhoto(response.getUserInfo().getPhoto());
                sessionManager.set_userEmail(ed_phone.getText().toString().trim());
                sessionManager.set_userPassword(ed_password.getText().toString().trim());

                if (sessionManager.getUserType().equals(Constants.TYPE_DOCTOR)) {
                    startActivity(new Intent(this, HomeActivityDoctor.class));
                    finish();

                }else if (sessionManager.getUserType().equals(Constants.TYPE_PATIENT)){
                    startActivity(new Intent(this, PatientNewActivity.class));
                    finish();
                }else if (sessionManager.getUserType().equals(Constants.TYPE_ASSISTANT)){
                    startActivity(new Intent(this, AssistantHomeActivity.class));
                    finish();
                }else {
                    Toast.makeText(this, "Unknown user type.Please login again", Toast.LENGTH_SHORT).show();
                }


            } else {
                MyDialog.getInstance().with(LoginActivity.this)
                        .message(object.getString("message"))
                        .autoBack(false)
                        .autoDismiss(false)
                        .show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
//        if (status.getStatus()){
//            Gson gson=new Gson();
//            sessionManager.set_userData(gson.toJson(status.getUserInfo()));
//            sessionManager.setToken("Bearer "+status.getAccessToken());
//            sessionManager.setuserType(Constants.TYPE_DOCTOR);
//            sessionManager.setLoggedIn(true);
//
//            if (status.getUserInfo().getUserType().equals(Constants.TYPE_DOCTOR)){
//                startActivity(new Intent(this,HomeActivityDoctor.class));
//                finish();
//
//            }
//
//
//
//        }else {
//            Gson gson=new Gson();
//            MyDialog.getInstance().with(LoginActivity.this)
//                    .message(gson.toJson(status))
//                    .autoBack(false)
//                    .autoDismiss(false)
//                    .show();
//        }
    }

    private void startDownloadTestNames() {
        Api.getInstance().downloadTestNames(this);
    }

    @Override
    public void onUserLoginFailed(String msg) {
        progressDialog.dismiss();
        MyDialog.getInstance().with(LoginActivity.this)
                .message(msg)
                .autoBack(false)
                .autoDismiss(false)
                .show();

    }

    @Override
    public void ontestNamesDownloadSuccess(List<TestModel> data) {
        DataStore.testModelList.clear();
        //   Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();
        for (int i = 0; i < data.size(); i++) {
            DataStore.testModelList.add(new testSelectedModel(false, data.get(i)));
        }
    }

    @Override
    public void ontestNamesDownloadFailed(String msg) {

    }
}
