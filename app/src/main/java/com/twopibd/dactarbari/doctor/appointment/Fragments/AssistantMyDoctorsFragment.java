package com.twopibd.dactarbari.doctor.appointment.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopibd.dactarbari.doctor.appointment.R;

public class AssistantMyDoctorsFragment extends Fragment {
    View v;

    Context context;

    public static AssistantMyDoctorsFragment newInstance() {
        AssistantMyDoctorsFragment fragment = new AssistantMyDoctorsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.assistant_my_doctors, container, false);
        context=getContext();

        return v;
    }





}