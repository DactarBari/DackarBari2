package com.twopibd.dactarbari.doctor.appointment.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Activity.LoginActivity;
import com.twopibd.dactarbari.doctor.appointment.R;


public class MyDialog {

    private static MyDialog mySnackBar;

    private View snackView;
    private String message;
    private int TIME_OUT = Snackbar.LENGTH_LONG;
    private int color = Color.GREEN;
    private String actionText = "Dismiss";
    private View.OnClickListener onClickListener;
    boolean autoDismiss = true;
    boolean autoBack = true;
    Activity activity;


    public static MyDialog getInstance() {

        if (mySnackBar == null) {
            mySnackBar = new MyDialog();
        }
        return mySnackBar;
    }

    public MyDialog with(Activity activity) {
        this.activity = activity;
        return this;
    }

    public MyDialog message(String message) {
        this.message = message;
        return this;
    }

    public MyDialog autoDismiss(boolean dismiss) {
        this.autoDismiss = dismiss;
        return this;
    }

    public MyDialog autoBack(boolean back) {
        this.autoBack = back;
        return this;
    }



    public MyDialog setTextColor(int color) {
        this.color = color;
        return this;
    }

    public MyDialog setTimeout(int timeout) {
        this.TIME_OUT = timeout;
        return this;
    }

    public MyDialog setActionText(String actionText) {
        this.actionText = actionText;
        return this;
    }

    public MyDialog setActionClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
        return this;
    }



    public void show() {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        tv_msg.setTextColor(activity.getResources().getColor(R.color.colorPrimary));
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        tv_msg.setText(message);


        dialog.show();
        TextView tv_close = (TextView) dialog.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (autoBack) {

                    activity.onBackPressed();
                }
            }
        });

        new CountDownTimer(3000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub

                if (dialog.isShowing()) {

                    if (autoDismiss) {
                        dialog.dismiss();
                    }
                    if (autoBack) {

                        activity.onBackPressed();
                    }


                }
            }
        }.start();


    }

    public void showMsgOnly() {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        tv_msg.setText(message);


        dialog.show();
        TextView tv_close = (TextView) dialog.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.onBackPressed();

            }
        });

//        new CountDownTimer(3000, 1000) {
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onFinish() {
//                // TODO Auto-generated method stub
//
//               if (autoDismiss){
//                if (dialog.isShowing()) {
//
//                    dialog.dismiss();
//                    // activity.onBackPressed();
//                }
//
//
//                }
//            }
//        }.start();


    }
    public void moveToLogin() {
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_layout);
        Window window = dialog.getWindow();
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        tv_msg.setText(message);


        dialog.show();
        TextView tv_close = (TextView) dialog.findViewById(R.id.tv_close);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                activity.startActivity(new Intent(activity, LoginActivity.class));
                activity.finishAffinity();
            }
        });

//        new CountDownTimer(3000, 1000) {
//
//            @Override
//            public void onTick(long millisUntilFinished) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onFinish() {
//                // TODO Auto-generated method stub
//
//               if (autoDismiss){
//                if (dialog.isShowing()) {
//
//                    dialog.dismiss();
//                    // activity.onBackPressed();
//                }
//
//
//                }
//            }
//        }.start();


    }


}
