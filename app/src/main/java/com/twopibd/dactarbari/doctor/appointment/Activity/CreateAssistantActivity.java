package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.TOKEN;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.USER_ID;

public class CreateAssistantActivity extends AppCompatActivity implements  ApiListener.assistantCreateListener{
    @BindView(R.id.ed_name)
    EditText ed_name;
    @BindView(R.id.ed_email)
    EditText ed_email;
    @BindView(R.id.ed_mobile)
    EditText ed_mobile;
    @BindView(R.id.ed_password)
    EditText ed_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_assistant);
        ButterKnife.bind(this);
        setUpStatusbar();

    }
    public  void setUpStatusbar(){
        if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true);
        }
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        //make fully Android Transparent Status bar
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
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
                return(true);
        }

        return(super.onOptionsItemSelected(item));
    }
    public void back(View view) {
        onBackPressed();

    }

    public void register(View view) {
        String name=ed_name.getText().toString().trim();
        String email=ed_email.getText().toString().trim();
        String mobile=ed_mobile.getText().toString().trim();
        String password=ed_password.getText().toString().trim();
        if (name.length()>0){
            if (email.length()>0){
                if (mobile.length()>0){
                    if (password.length()>0){
                        MyProgressBar.with(CreateAssistantActivity.this).show();
                        Api.getInstance().assistantRegister(TOKEN,"doctor_assistant",name,"N/A",email,password,mobile,"BD","N/A","N/A","N/A","N/A","N/A",USER_ID,this);


                    }
                }
            }
        }

    }

    @Override
    public void onAssistantCreateSuccess(StatusMessage data) {
        MyProgressBar.dismiss();

        if (data.getMessage()!=null){
            Toast.makeText(this, data.getMessage(), Toast.LENGTH_SHORT).show();
        }
        onBackPressed();

    }

    @Override
    public void onAssistantCreateFailed(String msg) {
        MyProgressBar.dismiss();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
