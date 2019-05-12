package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Activity.SendBookingActivity;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.singleDrModel;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.seelctedscheduleInfo;


/**
 * Created by mukul on 3/10/2019.
 */


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    Context context;
    List<ScheduleInfo> list = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day, tv_timeS,tv_timeE;

        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_timeS = (TextView) view.findViewById(R.id.tv_timeS);
            tv_timeE = (TextView) view.findViewById(R.id.tv_timeE);


        }
    }


    public ScheduleAdapter(List<ScheduleInfo> data) {
        this.list = data;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.days_single_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final ScheduleInfo data = list.get(position);
        context=holder.tv_day.getContext();

        holder.tv_day.setText(DataStore.convertToWeekDay(""+(data.getDay()-1)));
        holder.tv_timeS.setText(DataStore.changeDateformate1(data.getStartTime()));
        holder.tv_timeE.setText(DataStore.changeDateformate1( data.getEndTime()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //seelctedscheduleInfo=list;
                //context.startActivity(new Intent(context, SendBookingActivity.class));
                Toast.makeText(context, ""+data.getId(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}