package com.twopibd.dactarbari.doctor.appointment.Fragments;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopibd.dactarbari.doctor.appointment.R;

public class CreateAssistantFragment extends Fragment {
    View v;

    Context context;

    public static CreateAssistantFragment newInstance() {
        CreateAssistantFragment fragment = new CreateAssistantFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.create_assistant_fragment, container, false);
        context=getContext();

        return v;
    }





}