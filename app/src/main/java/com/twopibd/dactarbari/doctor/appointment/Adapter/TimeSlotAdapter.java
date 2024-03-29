package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Activity.AppointmentSubmitActivity;
import com.twopibd.dactarbari.doctor.appointment.Activity.SendBookingActivity;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.Slot;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mukul on 3/10/2019.
 */


public class TimeSlotAdapter extends RecyclerView.Adapter<TimeSlotAdapter.MyViewHolder> {
    List<Slot> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_timeStart,tv_timeEnd;
        LinearLayout linerBody;


        public MyViewHolder(View view) {
            super(view);
            tv_timeStart = (TextView) view.findViewById(R.id.tv_timeStart);
            tv_timeEnd = (TextView) view.findViewById(R.id.tv_timeEnd);
            linerBody = (LinearLayout) view.findViewById(R.id.linerBody);


        }
    }

    public void setData(List<Slot> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();


    }

    public void clearData() {
        list.clear();
        notifyDataSetChanged();


    }


    public TimeSlotAdapter(List<Slot> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_slot_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Slot movie = list.get(position);
        context = holder.tv_timeStart.getContext();
        holder.tv_timeStart.setText(DataStore.changeDateformate1(movie.getTime().substring(0, 7)));
        holder.tv_timeEnd.setText(DataStore.changeDateformate1(movie.getTime().substring(9, 16)));
//        if (true) {
//            holder.tv_time.setTextColor(context.getResources().getColor(R.color.colorPrimary));
//            final int sdk = android.os.Build.VERSION.SDK_INT;
//            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                holder.linerBody.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_selected));
//            } else {
//                holder.linerBody.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_selected));
//            }
//
//        } else {
//            holder.tv_time.setTextColor(context.getResources().getColor(R.color.gray_txt_clr));
//
//            final int sdk = android.os.Build.VERSION.SDK_INT;
//            if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
//                holder.linerBody.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.bg_unselected));
//            } else {
//                holder.linerBody.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_unselected));
//            }
//
//        }
        holder.itemView.setOnClickListener((View v) -> slotClicked(position));


    }

    private void slotClicked(int position) {
       // Toast.makeText(context, "Sloat ID : " + list.get(position).getSchedule_id(), Toast.LENGTH_SHORT).show();
        DataStore.selectedSloat = list.get(position);
        context.startActivity(new Intent(context, AppointmentSubmitActivity.class));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}