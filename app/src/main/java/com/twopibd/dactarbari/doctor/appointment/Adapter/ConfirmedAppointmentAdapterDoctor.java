package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyProgressBar;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.KEY;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.USER_ID;

/**
 * Created by mukul on 3/10/2019.
 */


public class ConfirmedAppointmentAdapterDoctor extends RecyclerView.Adapter<ConfirmedAppointmentAdapterDoctor.MyViewHolder> {
    List<AppointmentModels> list = new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_address, tv_problem, tv_date, tv_refID, tv_time;
        ImageView  img_delete;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_address = (TextView) view.findViewById(R.id.tv_address);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            tv_problem = (TextView) view.findViewById(R.id.tv_problem);
            tv_refID = (TextView) view.findViewById(R.id.tv_refID);
            img_delete = (ImageView) view.findViewById(R.id.img_delete);
            tv_time = (TextView) view.findViewById(R.id.tv_time);


        }
    }


    public ConfirmedAppointmentAdapterDoctor(List<AppointmentModels> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_confirmed_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModels movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getAppointmentFor());
        holder.tv_address.setText(movie.getChamber_address());
        holder.tv_date.setText(movie.getDate());
        holder.tv_refID.setText("" + movie.getId());
        holder.tv_problem.setText(movie.getCurrentProblems());
        holder.tv_time.setText(movie.getSlotStartTime());
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogList.getInstance().with((Activity) context).yesNoConfirmation(new MyDialogList.confirmListener() {
                    @Override
                    public void onDialogClicked(boolean result) {
                        if (result) {

                            SessionManager sessionManager = new SessionManager(context);
                            changeState(KEY, "" + movie.getId(), position, "3",USER_ID);
                        } else {
//                            SessionManager sessionManager=new SessionManager(context);
//                            KEY=sessionManager.getToken();
//                            changeState(KEY,""+movie.getId(), position,"2");
                        }
                    }
                }, "Do you want to delete this appointment?");
            }
        });


    }

    public boolean removeItem(int position) {
        if (list.size() >= position + 1) {
            list.remove(position);
            return true;
        }
        return false;
    }

    public void changeState(String KEY, String appointment_id, int pos, String status, String userId) {
        MyProgressBar.with(context);
        //Api.getInstance().changeStatus(appointment_id, "1", this);
        Api.getInstance().changeAppintmentStatus(KEY, appointment_id, status,userId, new ApiListener.AppintmentChangeListener() {
            @Override
            public void onAppintmentChangeSuccess(StatusMessage data) {
                MyProgressBar.dismiss();
                Toast.makeText(context, data.getMessage(), Toast.LENGTH_SHORT).show();
                if (removeItem(pos)) {
                    notifyItemRemoved(pos);
                    notifyItemRangeChanged(pos, getItemCount());
                }
            }

            @Override
            public void onAppintmentChangeFailed(String msg) {
                MyProgressBar.dismiss();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}