package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Fragments.HomeFragment;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivityDoctor extends AppCompatActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        init_display();




    }



    public static void setLightStatusBar(View view, Activity activity){


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    private void init_display() {
        tv_name.setText(sessionManager.getUserName());
        Picasso.with(HomeActivityDoctor.this).load(Constants.PHOTO_BASE+sessionManager.get_userPhoto()).into(profile_image);
        profile_image.setOnClickListener((View v)->openDrPersonalInfoActivity());
    }

    private void openDrPersonalInfoActivity() {
        startActivity(new Intent(this,DrPersonalInfoActivity.class));
        finish();

    }

    public void openScheduleDr(View view) {
        startActivity(new Intent(this,ScheduleMenuActivity.class));
    }

    public void openCalender(View view) {
        new CalendarDialog(this, new OnDaysSelectionListener() {
            @Override
            public void onDaysSelected(List<Day> selectedDays) {
                Toast.makeText(HomeActivityDoctor.this, ""+selectedDays.size()+" days selected", Toast.LENGTH_SHORT).show();

            }
        }).show();
    }

    public void OpenProfile(View view) {
        openDrPersonalInfoActivity();
    }

    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this,LoginActivity.class));
        finishAffinity();
    }
}
