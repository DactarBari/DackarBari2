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
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusResponse;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyProgressBar;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mukul on 3/10/2019.
 */


public class PendingAppointmentAdapterDoctor extends RecyclerView.Adapter<PendingAppointmentAdapterDoctor.MyViewHolder> implements ApiListener.appointmentStateChangeListener {
    List<AppointmentModel> list = new ArrayList<>();

    Context context;
    int triggeredItem = 0;
    List<String> TestList = new ArrayList<>();
    int pos;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_problem, tv_date;
        ImageView circleImageView;
        RelativeLayout relative_container;
        TextView cardPrescribeTest;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_problem = (TextView) view.findViewById(R.id.tv_problem);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            cardPrescribeTest = (TextView) view.findViewById(R.id.cardPrescribeTest);


        }
    }


    public PendingAppointmentAdapterDoctor(List<AppointmentModel> lists) {
        this.list = lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pending_appointment_dr, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getAppointmentFor());
        holder.tv_problem.setText(movie.getProblems());
        holder.tv_date.setText(movie.getDate());

        holder.itemView.setOnClickListener((View v) -> changeState(movie.getAppointment_id(), position));
        holder.cardPrescribeTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialogList.getInstance().with((Activity) context).showTestList(new MyDialogList.testSelectedListener() {
                    @Override
                    public void onDialogCloased(List<String> selectedTest) {
                        TestList.clear();
                        TestList.addAll(selectedTest);

                        if (TestList.size() > 0) {
                            pos=position;
                            MyProgressBar.with(context).show();
                            addRecommendTest(movie.getAppointment_id(), TestList.get(0), 0);

                        }

                    }
                });
            }
        });

    }

    private void addRecommendTest(String appointment_id, String s, int index) {
        Api.getInstance().postRecommendationTest(appointment_id, s, new ApiListener.recomendationTestPostListener() {
            @Override
            public void onrecomendationTestPostSuccess(StatusResponse response) {

                int in = 1 + index;
                if (TestList.size() > in) {

                    addRecommendTest(appointment_id, TestList.get(in), in);
                } else {
                    MyProgressBar.dismiss();
                    changeToRecommended(appointment_id, pos);
                }


            }

            @Override
            public void onrecomendationTestPostFailed(String msg) {
                MyProgressBar.dismiss();
                Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();


            }
        });
    }


    public void changeState(String appointment_id, int pos) {
        MyProgressBar.with(context);
        triggeredItem = pos;
        Api.getInstance().changeStatus(appointment_id, "1", this);

    }
    public void changeToRecommended(String appointment_id, int pos) {
        MyProgressBar.with(context);
        triggeredItem = pos;
        Api.getInstance().changeStatus(appointment_id, "2", this);

    }

    @Override
    public void onAppointmentChangeSuccess(StatusResponse status) {
        MyProgressBar.dismiss();
        if (status.getStatus()) {
            MyDialog.getInstance().with((Activity) context)
                    .message("This appointment has been confirmed")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
            // list.remove(triggeredItem);
            if (removeItem(triggeredItem)) {
                notifyItemRemoved(triggeredItem);
                notifyItemRangeChanged(triggeredItem, getItemCount());
            }

        } else {
            MyDialog.getInstance().with((Activity) context)
                    .message("Failed")
                    .autoBack(false)
                    .autoDismiss(false)
                    .show();
        }

    }

    public boolean removeItem(int position) {
        if (list.size() >= position + 1) {
            list.remove(position);
            return true;
        }
        return false;
    }

    @Override
    public void onPppointmentChangeFailed(String msg) {
        MyProgressBar.dismiss();
        MyDialog.getInstance().with((Activity) context)
                .message("Failed")
                .autoBack(false)
                .autoDismiss(false)
                .showMsgOnly();

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}