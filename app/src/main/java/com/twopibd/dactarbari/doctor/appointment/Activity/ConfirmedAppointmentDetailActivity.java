package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.appointmentModel;


public class ConfirmedAppointmentDetailActivity extends AppCompatActivity {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.tv_appointmentfor)
    TextView tv_appointmentfor;
    @BindView(R.id.tv_date)
    TextView tv_date;
    @BindView(R.id.tv_spacialist)
    TextView tv_spacialist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed_appointment_detail);
        ButterKnife.bind(this);

        tv_name.setText(appointmentModel.getDrName());
        tv_address.setText(appointmentModel.getAddress());
        tv_appointmentfor.setText(appointmentModel.getAppointmentFor());
        tv_date.setText(appointmentModel.getDate());
        tv_spacialist.setText(appointmentModel.getSpacialist()+" specialist");
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }
}
