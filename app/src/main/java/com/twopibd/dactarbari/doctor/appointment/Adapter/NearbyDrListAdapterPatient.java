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

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Activity.ChamberDetailsActivityDr;
import com.twopibd.dactarbari.doctor.appointment.Activity.HomeActivityDoctor;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.chamberModel;


/**
 * Created by mukul on 3/10/2019.
 */


public class NearbyDrListAdapterPatient extends RecyclerView.Adapter<NearbyDrListAdapterPatient.MyViewHolder> {
    List<SearchModel> list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_address, tv_time, tv_lastDegree, tv_department;
        CircleImageView profile_image;

        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_department = (TextView) view.findViewById(R.id.tv_department);
            profile_image = (CircleImageView) view.findViewById(R.id.profile_image);



        }
    }


    public NearbyDrListAdapterPatient(List<SearchModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_box_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SearchModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.tv_department.setText(movie.getDepartmentName());
        Picasso.with(context).load(Constants.PHOTO_BASE+movie.getPhoto()).into(holder.profile_image);




    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}