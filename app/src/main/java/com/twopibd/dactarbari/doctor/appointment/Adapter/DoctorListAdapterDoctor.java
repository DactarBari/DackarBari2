package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Activity.ChamberDetailActivity;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.PHOTO_BASE;


/**
 * Created by mukul on 3/10/2019.
 */


public class DoctorListAdapterDoctor extends RecyclerView.Adapter<DoctorListAdapterDoctor.MyViewHolder> {
    List<SearchModel> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_department, tv_address;
        ImageView image;
        TextView cardBook,tv_hospitals;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_department = (TextView) view.findViewById(R.id.tv_department);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            image = (ImageView) view.findViewById(R.id.image);
            cardBook = (TextView) view.findViewById(R.id.cardBook);
            tv_hospitals = (TextView) view.findViewById(R.id.tv_hospitals);


        }
    }


    public DoctorListAdapterDoctor(List<SearchModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_book_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SearchModel data = list.get(position);
        holder.tv_name.setText(data.getName());
        context=holder.tv_name.getContext();
        holder.tv_department.setText(data.getDepartmentName());
        holder.tv_address.setText(data.getChamberAddress());
        Picasso.with(context).load(PHOTO_BASE+data.getPhoto()).into(holder.image);
        holder.cardBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataStore.selectedSearchModel=data;
                context.startActivity(new Intent(context, ChamberDetailActivity.class));


            }
        });
        String hospitals="";
        for (int i=0;i<data.getCurrentHospitals().size();i++){
            hospitals+=data.getCurrentHospitals().get(i).getName();
            if (!(i==(data.getCurrentHospitals().size()-1))){
                hospitals+="\n";

            }

        }
        holder.tv_hospitals.setText(hospitals);


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}