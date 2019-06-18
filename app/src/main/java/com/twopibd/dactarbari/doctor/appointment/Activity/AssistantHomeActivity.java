package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Fragments.AssistantAppointmentsFragment;
import com.twopibd.dactarbari.doctor.appointment.Fragments.AssistantMyDoctorsFragment;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.CustomDrawerButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AssistantHomeActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.linear_1)
    LinearLayout linear_1;
    @BindView(R.id.linear_2)
    LinearLayout linear_2;
    @BindView(R.id.customDrawer)
    CustomDrawerButton customDrawerButton;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assistant_home);
        ButterKnife.bind(this);

        sessionManager=new SessionManager(this);
        Data.sessionManager=sessionManager;
        Data.USER_ID=sessionManager.getUserId();
        Data.TOKEN=sessionManager.getToken();
        linear_1.setOnClickListener(this);
        linear_2.setOnClickListener(this);
        loadFragment(new AssistantAppointmentsFragment());
        customDrawerButton.setDrawerLayout( drawer_layout );
        customDrawerButton.getDrawerLayout().addDrawerListener( customDrawerButton );
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
    }
    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.linear_1:

                loadFragment(new AssistantAppointmentsFragment());
                linear_1.setAlpha(1);
                linear_2.setAlpha(0.5f);
                return;

            case R.id.linear_2:

                //AssistantMyDoctorsFragment
                loadFragment(new AssistantMyDoctorsFragment());
                linear_1.setAlpha(0.5f);
                linear_2.setAlpha(1);
                return;
        }
    }

    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }
}
