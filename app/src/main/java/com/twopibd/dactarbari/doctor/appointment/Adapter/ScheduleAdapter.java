package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.R;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.singleDrModel;


/**
 * Created by mukul on 3/10/2019.
 */


public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.MyViewHolder> {

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day, tv_time;

        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_time = (TextView) view.findViewById(R.id.tv_time);


        }
    }


    public ScheduleAdapter() {


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.days_single_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Day movie = singleDrModel.getDays().get(position);

        holder.tv_day.setText(DataStore.convertToWeekDay (movie.getDay()));
        holder.tv_time.setText(movie.getStart_time());


    }

    @Override
    public int getItemCount() {
        return singleDrModel.getDays().size();
    }
}