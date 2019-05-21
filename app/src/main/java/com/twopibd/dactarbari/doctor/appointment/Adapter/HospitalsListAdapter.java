package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Activity.DrListByDepartmentActivity;
import com.twopibd.dactarbari.doctor.appointment.Activity.DrListByHospitalActivity;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.departmentID;


/**
 * Created by mukul on 3/10/2019.
 */


public class HospitalsListAdapter extends RecyclerView.Adapter<HospitalsListAdapter.MyViewHolder> {
    List<HospitalModel> list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);



        }
    }


    public HospitalsListAdapter(List<HospitalModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.departments_item_big, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final HospitalModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataStore.hospitalID=movie;
               context.startActivity(new Intent(context, DrListByHospitalActivity.class));
            }
        });


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}