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

import com.twopibd.dactarbari.doctor.appointment.Activity.ChamberDetailsActivityDr;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.chamberModel;


/**
 * Created by mukul on 3/10/2019.
 */


public class ChamberListAdapterDoctor extends RecyclerView.Adapter<ChamberListAdapterDoctor.MyViewHolder> {
    List<ChamberModel> list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_days, tv_address, tv_time, tv_lastDegree, tv_epacialist;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_days = (TextView) view.findViewById(R.id.tv_days);
            tv_address = (TextView) view.findViewById(R.id.tv_address);



        }
    }


    public ChamberListAdapterDoctor(List<ChamberModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chamber_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ChamberModel movie = list.get(position);
        context = holder.tv_days.getContext();
        holder.tv_address.setText(movie.getChamberAddress());
        String days="";

        if(movie.getScheduleInfo().size()>0) {
            for (int i = 0; i < movie.getScheduleInfo().size(); i++) {
                days += DataStore.convertToWeekDay("" + (movie.getScheduleInfo().get(i).getDay() - 1)) + "-";
            }
        }else {
            days="No schedule for this chamber";
        }

        holder.tv_days.setText(days.substring(0,days.length()-1));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chamberModel=movie;
                context.startActivity(new Intent(context, ChamberDetailsActivityDr.class));

            }
        });


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}