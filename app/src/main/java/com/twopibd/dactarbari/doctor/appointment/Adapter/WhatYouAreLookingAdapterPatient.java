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

import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Activity.DrDepartmentsActivity;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Model.FeatureType;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by mukul on 3/10/2019.
 */


public class WhatYouAreLookingAdapterPatient extends RecyclerView.Adapter<WhatYouAreLookingAdapterPatient.MyViewHolder> {
    List<FeatureType> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_address, tv_time, tv_lastDegree, tv_type;
        CircleImageView profile_image;
        ImageView photo;

        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            photo = (ImageView) view.findViewById(R.id.photo);


        }
    }


    public WhatYouAreLookingAdapterPatient(List<FeatureType> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.department_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final FeatureType movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.tv_type.setText(movie.getType());
        Picasso.with(context).load(movie.getPhoto()).into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (position==0){
                    context.startActivity(new Intent(context, DrDepartmentsActivity.class));
                }
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}