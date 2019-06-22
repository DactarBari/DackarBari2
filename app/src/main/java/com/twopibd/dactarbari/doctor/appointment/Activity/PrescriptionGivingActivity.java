package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionGivingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_giving);
    }
    public void back(View view){
        onBackPressed();
    }

    public void AddMedicine(View view) {

            final Dialog dialog = new Dialog(PrescriptionGivingActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.add_medicine_dialog);
            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            List<String> medicines=new ArrayList<>();
            medicines.add("Medicine Name 1");
            medicines.add("Medicine Name 2");
            medicines.add("Medicine Name 3");
            medicines.add("Medicine Name 4");

            TextView tv_add = (TextView) dialog.findViewById(R.id.tv_add);
            tv_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                }
            });


            Spinner spinnerMedicineName = (Spinner) dialog.findViewById(R.id.spinnerMedicineName);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, medicines);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerMedicineName.setAdapter(dataAdapter);

            //duration spinner
            List<String>durations=new ArrayList<>();
            durations.add("Days");
            durations.add("Weeks");
            durations.add("Months");
            Spinner spinnerDuration = (Spinner) dialog.findViewById(R.id.spinnerDuration);
            ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, durations);
            dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerDuration.setAdapter(dataAdapter1);

        }

    public void diseaseHistory(View view) {
        startActivity(new Intent(this,PatientDiseaseSumActivity.class));
    }

    public void OldPrescriptionActivity(View view) {
        //PrescriptionActivityPatient
        startActivity(new Intent(this,PrescriptionActivityPatient.class));

    }
}
