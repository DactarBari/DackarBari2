package com.twopibd.dactarbari.doctor.appointment.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Adapter.ConfirmedAppointmentAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Adapter.PendingAppointmentAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.ListenerPatientsData;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentResponse;
import com.twopibd.dactarbari.doctor.appointment.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class AppointmentsListPatient extends Fragment implements ApiListener.patientAllDataDownloadListener {
    View v;

    int ALL_SHOWING = 1;
    int CONFIRMED = 0;
    int showing = CONFIRMED;

    public AppointmentsListPatient() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_appointment_patient, container, false);

        ButterKnife.bind(this, v);

        ListenerPatientsData.setPatientALLDataDownloadListener(this);
        return v;
    }

    private void selectPending() {


    }

    private void selectConfirmed() {



    }


    @Override
    public void onDownloaded(AppointmentResponse status) {



    }
}
