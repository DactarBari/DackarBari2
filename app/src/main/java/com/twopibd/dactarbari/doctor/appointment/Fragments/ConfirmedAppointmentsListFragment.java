package com.twopibd.dactarbari.doctor.appointment.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopibd.dactarbari.doctor.appointment.R;

import butterknife.ButterKnife;


public class ConfirmedAppointmentsListFragment extends Fragment {
    View v;

    public ConfirmedAppointmentsListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_confirmed_assistant, container, false);

        ButterKnife.bind(this,v);

        return v;
    }


}
