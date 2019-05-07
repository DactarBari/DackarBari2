package com.twopibd.dactarbari.doctor.appointment.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.TYPE_DOCTOR;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.TYPE_PATIENT;


public class SplashActivity extends Activity {
    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager = new SessionManager(SplashActivity.this);


        View mSplashImage = findViewById(R.id.splash);
        TextView mSplashText = findViewById(R.id.splashText);
        Animation splashAnimImage = AnimationUtils.loadAnimation(this, R.anim.splash_anim_img);
        Animation splashAnimText = AnimationUtils.loadAnimation(this, R.anim.splash_anim);
        mSplashText.startAnimation(splashAnimText);
        mSplashImage.startAnimation(splashAnimImage);

        splashAnimImage.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if (false) {

                    {
                        if (sessionManager.getUserType().equals(TYPE_DOCTOR)){
                            startActivity(new Intent(SplashActivity.this, DoctorHomeActivity.class));
                            finish();
                        }else   if (sessionManager.getUserType().equals(TYPE_PATIENT)){
                            startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                            finish();
                        }else {
                            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                            finish();
                        }



                    }

                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();

                }

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }




    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}