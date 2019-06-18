package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.twopibd.dactarbari.doctor.appointment.Activity.AddWeeklyChamberActivity;
import com.twopibd.dactarbari.doctor.appointment.Model.DaysTimeModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mukul on 3/10/2019.
 */


public class DaysTimesAdapterDoctor extends RecyclerView.Adapter<DaysTimesAdapterDoctor.MyViewHolder> {

    Context context;
    private int mYear, mMonth, mDay, mHour, mMinute;
    int TYPE_START = 0;
    int TYPE_END = 1;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_startDay, tv_endDay;
        ImageView circleImageView;
        RelativeLayout relative_container;
        CheckBox checkBox;


        public MyViewHolder(View view) {
            super(view);
            tv_startDay = (TextView) view.findViewById(R.id.tv_startDay);
            tv_endDay = (TextView) view.findViewById(R.id.tv_endDay);
            checkBox = (CheckBox) view.findViewById(R.id.checkbox_day);


        }
    }


    public DaysTimesAdapterDoctor() {


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.days_start_time_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final DaysTimeModel movie = AddWeeklyChamberActivity.Dayslist.get(position);
        context = holder.tv_startDay.getContext();
        if (movie.isSelected()) {
            holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);

        }
        holder.checkBox.setText(movie.getDayName());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    AddWeeklyChamberActivity.Dayslist.get(position).setSelected(true);
                } else {
                    AddWeeklyChamberActivity.Dayslist.get(position).setSelected(false);

                }
            }
        });
        holder.tv_startDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker(holder.tv_startDay, position, TYPE_START);
            }
        });
        holder.tv_endDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker(holder.tv_endDay, position, TYPE_END);
            }
        });

    }

    private void showPicker(TextView textView, int position, int TYPE) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String formatedTime = formateDate(hourOfDay, minute);
                        if (TYPE == TYPE_START)
                            AddWeeklyChamberActivity.Dayslist.get(position).setStartTime(doubleDigit(hourOfDay)+":"+doubleDigit(minute));
                        else
                            AddWeeklyChamberActivity.Dayslist.get(position).setEndTime(doubleDigit(hourOfDay)+":"+doubleDigit(minute));

                        textView.setText(formatedTime);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }



    private String formateDate(int hourOfDay, int minute) {
        String newHour = doubleDigit(hourOfDay % 12);
        String newMinute = doubleDigit(minute);
        String am_pm = amPm(hourOfDay);
        return (newHour + " : " + newMinute + " " + am_pm);
    }

    private String amPm(int value) {

        return value > 12 ? "PM" : "AM";
    }

    private String doubleDigit(int value) {

        String s1 = "0" + value;
        String s2 = "" + value;
        return value < 10 ? s1 : s2;
    }


    @Override
    public int getItemCount() {
        return AddWeeklyChamberActivity.Dayslist.size();
    }
}