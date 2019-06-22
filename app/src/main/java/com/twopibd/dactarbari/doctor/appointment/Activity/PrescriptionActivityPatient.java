package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.twopibd.dactarbari.doctor.appointment.R;

public class PrescriptionActivityPatient extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_patient);
    }
    public void back(View view){
        onBackPressed();
    }
    public   void show_dialog(View view) {
        final Dialog dialog = new Dialog(PrescriptionActivityPatient.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.prescription_dialog);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
