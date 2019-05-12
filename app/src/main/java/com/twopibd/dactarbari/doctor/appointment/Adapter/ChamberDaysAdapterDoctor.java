package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;


/**
 * Created by mukul on 3/10/2019.
 */


public class ChamberDaysAdapterDoctor extends RecyclerView.Adapter<ChamberDaysAdapterDoctor.MyViewHolder>  {
    List<ScheduleInfo> list=new ArrayList<>();

    Context context;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day, tv_start, tv_end, tv_capacity;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_start = (TextView) view.findViewById(R.id.tv_start);
            tv_end = (TextView) view.findViewById(R.id.tv_end);
            tv_capacity = (TextView) view.findViewById(R.id.tv_capacity);


        }
    }


    public ChamberDaysAdapterDoctor(List<ScheduleInfo> lists ) {
        this.list=lists;

    }
    public void addToAdapter(ScheduleInfo data ) {
        list.add(data);
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chamber_days_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final ScheduleInfo movie = list.get(position);
        context = holder.tv_day.getContext();
        SessionManager sessionManager=new SessionManager(context);
        String key=sessionManager.getToken();
        holder.tv_day.setText(DataStore.convertToWeekDay(""+(movie.getDay()-1)));
        holder.tv_start.setText(movie.getStartTime());
        holder.tv_end.setText(movie.getEndTime());
        holder.tv_capacity.setText(""+movie.getPatientCapacity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyProgressDialog.with(context);
                MyDialogList.getInstance().with((Activity)context).yesNoConfirmation(new MyDialogList.confirmListener() {
                    @Override
                    public void onDialogClicked(boolean result) {
                        if (result){
                            Api.getInstance().deleteSchedule(key, "" + movie.getId(), new ApiListener.drScheduleDeleteListener() {
                                @Override
                                public void onScheduleDeleteSuccess(StatusMessage data) {
                                    MyProgressDialog.destroy();
                                    if (data.getStatus()){
                                        Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                                        list.remove(position);
                                        notifyItemRemoved(position);
                                        notifyItemRangeChanged(position,list.size());
                                    }

                                }

                                @Override
                                public void onScheduleDeleteFailed(String msg) {
                                    MyProgressDialog.destroy();
                                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                                }
                            });
                        }else {
                            Toast.makeText(context, "Canceled", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, "Do you really want to delete this schedule?");


            }
        });



    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}