package com.twopibd.dactarbari.doctor.appointment.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.twopibd.dactarbari.doctor.appointment.Adapter.DepartmentListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DepartmentsFragment extends Fragment implements ApiListener.DepartmentListDownlaodListener {
    View v;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    Context context;

    public DepartmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.department_fragment, container, false);
        context=v.getContext();
        ButterKnife.bind(this, v);
        Api.getInstance().downloadDepartments(this);
        return v;
    }

    @Override
    public void onDepartmentLisDownloadSuccess(List<DepartmentModel> data) {
        DepartmentListAdapterDoctor mAdapter = new DepartmentListAdapterDoctor(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onDepartmentLisDownloadFailed(String msg) {

    }
}