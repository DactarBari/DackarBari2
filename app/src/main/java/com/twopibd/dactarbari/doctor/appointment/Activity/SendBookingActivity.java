package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.badoualy.datepicker.DatePickerTimeline;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusResponse;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyProgressBar;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.singleDrModel;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.seelctedscheduleInfo;


public class SendBookingActivity extends AppCompatActivity implements ApiListener.appoinetmentPOstListener {

    SessionManager sessionManager;
    List<Integer> weekDays = new ArrayList<>();
    Calendar calendar;


    int date, month;
    String appointmentDate;
    Calendar todayCalender;
    @BindView(R.id.dateTimeline)
    DatePickerTimeline dateTimeline;
    String ids;
    HashMap<Integer,List<Integer>> scheduleList = new HashMap<Integer,List<Integer>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_booking);
        ButterKnife.bind(this);
        calendar = Calendar.getInstance();
        todayCalender = Calendar.getInstance();
        date = calendar.getTime().getDate();
        month = calendar.getTime().getMonth();
        sessionManager = new SessionManager(this);
        weekDays.clear();
        for (int i = 0; i < seelctedscheduleInfo.size(); i++) {
            List<ScheduleInfo> d = seelctedscheduleInfo;
            weekDays.add((d.get(i).getDay() - 1));
            if (scheduleList.get(seelctedscheduleInfo.get(i).getDay())==null){
                List<Integer>list=new ArrayList<>();
                list.add(seelctedscheduleInfo.get(i).getId());
                scheduleList.put(seelctedscheduleInfo.get(i).getDay(),list);

            }else {
                List<Integer>list=new ArrayList<>();
                list.addAll( scheduleList.get(seelctedscheduleInfo.get(i).getDay()));
                list.add(seelctedscheduleInfo.get(i).getId());
                scheduleList.put(seelctedscheduleInfo.get(i).getDay(),list);

            }

        }
        String data="";

        Iterator it = scheduleList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
            data+=pair.getKey() + " = " + pair.getValue()+"\n";

        }
        Toast.makeText(this, data, Toast.LENGTH_LONG).show();
      //  Toast.makeText(this, ""+SYSte, Toast.LENGTH_SHORT).show();



        //Check();

        dateTimeline.setOnDateSelectedListener(new DatePickerTimeline.OnDateSelectedListener() {
            @Override
            public void onDateSelected(int year, int month, int day, int index) {
                Calendar calendar=Calendar.getInstance();
                calendar.set(year,month,day);
                Toast.makeText(SendBookingActivity.this, DataStore.convertToWeekDay(""+calendar.getTime().getDay()), Toast.LENGTH_SHORT).show();
               // Toast.makeText(SendBookingActivity.this,""+ calendar.getTime().getDay(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void previousDate() {
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        if (calendar.after(todayCalender)) {

            MinusCheck();
        } else {
            Toast.makeText(this, "Cannot appoint for previousday", Toast.LENGTH_SHORT).show();
        }
    }

    public void nextDate() {
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Check();


    }

    private void Check() {

        date = calendar.getTime().getDate();

        month = calendar.getTime().getMonth();
        calendar.setTime(new Date(calendar.getTime().getYear(), month, date));


        boolean isAvailable = getIsDrChamberOpen(calendar);
        if (isAvailable) {
            //  Toast.makeText(this, "Dr is available today", Toast.LENGTH_SHORT).show();
            appointmentDate = "" + 2019 + "-" + String.valueOf(calendar.getTime().getMonth() + 1) + "-" + calendar.getTime().getDate() + " " + "00:00:00";
            //Toast.makeText(this, appointmentDate, Toast.LENGTH_LONG).show();
            String date = "" + Calendar.getInstance().get(Calendar.YEAR) + "-" + doubleDigit(calendar.getTime().getMonth() + 1) + "-" + doubleDigit(calendar.getTime().getDate());
           // Toast.makeText(this, date, Toast.LENGTH_LONG).show();


        } else {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            Check();

        }


    }

    private String doubleDigit(int value) {

        String s1 = "0" + value;
        String s2 = "" + value;
        return value < 10 ? s1 : s2;
    }

    private void MinusCheck() {

        date = calendar.getTime().getDate();

        month = calendar.getTime().getMonth();
        calendar.setTime(new Date(calendar.getTime().getYear(), month, date));


        boolean isAvailable = getIsDrChamberOpen(calendar);
        if (isAvailable) {
            //  Toast.makeText(this, "Dr is available today", Toast.LENGTH_SHORT).show();
            appointmentDate = "" + 2019 + "-" + String.valueOf(calendar.getTime().getMonth() + 1) + "-" + calendar.getTime().getDate() + " " + "00:00:00";
            Toast.makeText(this, "" + calendar.getTime().getYear() + "-" + calendar.getTime().getMonth() + "-" + calendar.getTime().getDate(), Toast.LENGTH_LONG).show();
            String date = "" + Calendar.getInstance().get(Calendar.YEAR) + "-" + doubleDigit(calendar.getTime().getMonth() + 1) + "-" + doubleDigit(calendar.getTime().getDate());
           // Toast.makeText(this, date, Toast.LENGTH_LONG).show();


        } else {
            calendar.add(Calendar.DAY_OF_MONTH, -1);
            MinusCheck();

        }


    }

    private boolean getIsDrChamberOpen(Calendar calendar) {
        boolean status = false;
        for (int d = 0; d < weekDays.size(); d++) {
            if (calendar.getTime().getDay() == weekDays.get(d)) {
                status = true;
                break;
            } else {
                status = false;
            }
        }


        return status;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

    public void submit(View view) {

        MyProgressBar.with(this);

        //Api.getInstance().postAppointment(sessionManager.getUserId(), singleDrModel.getId(), singleDrModel.getDr_id(), name, phone, problem,appointmentDate, this);
    }

    @Override
    public void onAppointmentPostSuccess(StatusResponse status) {
        MyProgressBar.dismiss();
        if (status.getStatus()) {
            MyDialog.getInstance().with(SendBookingActivity.this)
                    .message("Your appointment request is sent successfully.Please wait till confirmation")
                    .autoBack(false)
                    .autoDismiss(false)
                    .showMsgOnly();
        } else {
            MyDialog.getInstance().with(SendBookingActivity.this)
                    .message("Techinal error.Try again later")
                    .autoBack(false)
                    .autoDismiss(false)
                    .showMsgOnly();
        }
    }

    @Override
    public void onAppointmentPostFailed(String msg) {
        MyProgressBar.dismiss();

        MyDialog.getInstance().with(SendBookingActivity.this)
                .message("Techinal error.Try again later")
                .autoBack(false)
                .autoDismiss(false)
                .showMsgOnly();

    }

    public void back(View view) {
        onBackPressed();
    }
}
