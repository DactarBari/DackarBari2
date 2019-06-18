package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.twopibd.dactarbari.doctor.appointment.Fragments.AppointmentsListPatient;
import com.twopibd.dactarbari.doctor.appointment.Fragments.NotificationFragment;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.CustomDrawerButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class HomeActivity extends AppCompatActivity {
    SessionManager sessionManager;
    TabLayout tabLayout;
    ViewPager viewPager;
    CustomDrawerButton customDrawerButton;
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        sessionManager=new SessionManager(this);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setSelectedTabIndicatorColor(Color.WHITE);
        tabLayout.setTabTextColors(Color.parseColor("#E6E6E6"), Color.WHITE);
        tabLayout.setSelectedTabIndicatorHeight(5);
        tabLayout.setupWithViewPager(viewPager);
        //Api.getInstance().getAppointmentsBypatient(sessionManager.getUserId(),this);
        drawer= (DrawerLayout)findViewById(R.id.drawer_layout);
        customDrawerButton = (CustomDrawerButton)findViewById(R.id.customDrawer);
        customDrawerButton.setDrawerLayout( drawer );
        customDrawerButton.getDrawerLayout().addDrawerListener( customDrawerButton );
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new AppointmentsListPatient(), "My Appointments");
        adapter.addFragment(new NotificationFragment(), "Test Recomendation");
        viewPager.setAdapter(adapter);
    }

    public void FindDoctorActivity(View view) {
        startActivity(new Intent(this, FindDoctorActivity.class));
    }


    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
