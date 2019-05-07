package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.twopibd.dactarbari.doctor.appointment.Fragments.AppointmentFragment;
import com.twopibd.dactarbari.doctor.appointment.Fragments.HomeFragment;
import com.twopibd.dactarbari.doctor.appointment.Fragments.ShopFragment;
import com.twopibd.dactarbari.doctor.appointment.R;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PatientHomeActivity extends GPSOpenActivity implements View.OnClickListener {
    @BindView(R.id.linerhomebutton)
    LinearLayout linerHomeButton;
    @BindView(R.id.linerShopButton)
    LinearLayout linerShopButton;
    @BindView(R.id.linerAppointmentButton)
    LinearLayout linerAppointmentButton;
    Context context = this;
    Resources resources;
    int primaryClr, another;

    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_two)
    TextView tv_two;
    @BindView(R.id.tv_three)
    TextView tv_three;
    int anotherColorText=Color.BLACK;

    @BindView(R.id.img_one)
    ImageView img_one;
    @BindView(R.id.img_two)
    ImageView img_two;
    @BindView(R.id.img_three)
    ImageView img_three;

    LinearLayout.LayoutParams enable = new LinearLayout.LayoutParams(23, 23);
    LinearLayout.LayoutParams disbale = new LinearLayout.LayoutParams(20, 20);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_home);
        ButterKnife.bind(this);
        getColorManagement();
        linerHomeButton.setOnClickListener(this);
        linerShopButton.setOnClickListener(this);
        linerAppointmentButton.setOnClickListener(this);
        Fragment selectedFragment = null;
        selectedFragment = HomeFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();


    }

    private void getColorManagement() {
        resources = context.getResources();
        primaryClr = resources.getColor(R.color.colorPrimary);
        another = Color.WHITE;
    }

    public void do_0_1_0() {


        //iv.setLayoutParams(layoutParams);


        tv_one.setAlpha(0.5f);
        tv_two.setAlpha(1f);
        tv_three.setAlpha(0.5f);


        img_one.setAlpha(0.5f);
        img_two.setAlpha(1f);
        img_three.setAlpha(0.5f);

        img_one.setLayoutParams(disbale);
        img_two.setLayoutParams(enable);
        img_three.setLayoutParams(disbale);



    }
    public void do_1_0_0() {


        img_one.setAlpha(1f);
        img_two.setAlpha(0.5f);
        img_three.setAlpha(0.5f);

        tv_one.setAlpha(1f);
        tv_two.setAlpha(0.5f);
        tv_three.setAlpha(0.5f);

        img_one.setLayoutParams(enable);
        img_two.setLayoutParams(disbale);
        img_three.setLayoutParams(disbale);

    }
    public void do_0_0_1() {


        img_one.setAlpha(0.5f);
        img_two.setAlpha(0.5f);
        img_three.setAlpha(1f);

        tv_one.setAlpha(0.5f);
        tv_two.setAlpha(0.5f);
        tv_three.setAlpha(1f);

        img_one.setLayoutParams(disbale);
        img_two.setLayoutParams(disbale);
        img_three.setLayoutParams(enable);


    }
    @Override
    public void onClick(View view) {
        Fragment selectedFragment = null;
        switch (view.getId()) {
            case R.id.linerhomebutton:
                selectedFragment = HomeFragment.newInstance();
                do_0_1_0();
                break;
            case R.id.linerShopButton:
                selectedFragment = ShopFragment.newInstance();
                do_1_0_0();
                break;
            case R.id.linerAppointmentButton:
                selectedFragment = AppointmentFragment.newInstance();
                do_0_0_1();
                break;

        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, selectedFragment);
        transaction.commit();
    }
}
