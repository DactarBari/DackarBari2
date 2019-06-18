package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Data.locationPickListener;
import com.twopibd.dactarbari.doctor.appointment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChamberAddCommonInfoActivity extends AppCompatActivity {
    @BindView(R.id.ed_address)
    EditText ed_address;
    @BindView(R.id.ed_fees)
    EditText ed_fees;
    Context context = this;
    public static String CHAMBER_LATTITUDE = "";
    public static String CHAMBER_LONGITUDE = "";
    public static String FEES = "";
    String address = "";
    public static String ADDRESS = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamber_add_common_info);
        ButterKnife.bind(this);
        locationPickListener.setlocationPickerListener(new ApiListener.LocationPicked() {
            @Override
            public void onPicked(boolean isPicked) {
                Toast.makeText(ChamberAddCommonInfoActivity.this, "locaton picked", Toast.LENGTH_SHORT).show();
                address = Data.getAddress(context, DataStore.selectedLocation);
                ed_address.setText(address);
                CHAMBER_LATTITUDE = "" + DataStore.selectedLocation.latitude;
                CHAMBER_LONGITUDE = "" + DataStore.selectedLocation.longitude;
                ADDRESS = address;
            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }


    public void openMap(View view) {
        startActivity(new Intent(this, MapsActivity3.class));
    }

    public void next(View view) {
        FEES=ed_fees.getText().toString().trim();

        startActivity(new Intent(this, AddWeeklyChamberActivity.class));
    }
}
