package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.DepartmentListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.DoctorListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DrListByDepartmentActivity extends AppCompatActivity implements  ApiListener.drSearchListener {
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_name)
    TextView tv_name;
    SessionManager sessionManager;
    String key;
    String USER_ID;
    Context context=this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_list_by_department);
        ButterKnife.bind(this);
        sessionManager=new SessionManager(this);
        key=sessionManager.getToken();
        USER_ID=sessionManager.getUserId();
        int LENGTH=15;
        if (DataStore.departmentID.getName().length()>=LENGTH){
            tv_name.setText(DataStore.departmentID.getName().substring(0,LENGTH)+"......");
        }else {
            tv_name.setText(DataStore.departmentID.getName());

        }
        Api.getInstance().searchByDepartmentDoctor(key,""+DataStore.departmentID.getId(),this);

    }

    @Override
    public void onSearchSuccess(List<SearchModel> data) {
       // Toast.makeText(this, ""+data.size(), Toast.LENGTH_SHORT).show();
        DoctorListAdapterDoctor mAdapter = new DoctorListAdapterDoctor(data);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);

    }

    @Override
    public void onSearchFailed(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }

    public void back(View view) {
        onBackPressed();
    }
}
