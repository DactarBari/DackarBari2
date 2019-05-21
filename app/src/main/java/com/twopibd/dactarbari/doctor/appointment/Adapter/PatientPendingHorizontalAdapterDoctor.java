package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mukul on 3/10/2019.
 */


public class PatientPendingHorizontalAdapterDoctor extends RecyclerView.Adapter<PatientPendingHorizontalAdapterDoctor.MyViewHolder> {
    List<AppointmentModels> list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_sloat, tv_date, tv_time, tv_lastDegree, tv_appointmentfor,tv_address;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_sloat = (TextView) view.findViewById(R.id.tv_sloat);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_appointmentfor = (TextView) view.findViewById(R.id.tv_appointmentfor);


        }
    }


    public PatientPendingHorizontalAdapterDoctor(List<AppointmentModels> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.patient_pending_item_hor, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModels data = list.get(position);
        context = holder.tv_sloat.getContext();
        holder.tv_sloat.setText(data.getSlotStartTime()+" to "+data.getSlotEndTime());
        holder.tv_date.setText(data.getDate());
        holder.tv_appointmentfor.setText(data.getAppointmentFor());


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}