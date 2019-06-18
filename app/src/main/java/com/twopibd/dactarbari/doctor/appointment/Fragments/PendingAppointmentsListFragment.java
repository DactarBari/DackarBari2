package com.twopibd.dactarbari.doctor.appointment.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopibd.dactarbari.doctor.appointment.Adapter.ConfirmedAppointmentAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.lis;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PendingAppointmentsListFragment extends Fragment {
    View v;

    public PendingAppointmentsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_appointments_list, container, false);

        ButterKnife.bind(this,v);

        return v;
    }


}
