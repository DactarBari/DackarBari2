package com.twopibd.dactarbari.doctor.appointment.Widgets;

import android.app.ProgressDialog;
import android.content.Context;

public class MyProgressDialog {
    public  static ProgressDialog progressDialog;
    public static void with(Context context){

        if (true){
            progressDialog=new ProgressDialog(context);
            progressDialog.setMessage("Please wait");
            progressDialog.show();
        }else {
            progressDialog.show();
        }
    }
    public static void with(Context context,String message){

        if (true){
            progressDialog=new ProgressDialog(context);
            progressDialog.setMessage(message);
            progressDialog.show();
        }else {
            progressDialog.show();
        }
    }
    public static void destroy(){
        if (progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }
}
