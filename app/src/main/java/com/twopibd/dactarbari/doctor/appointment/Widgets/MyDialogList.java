package com.twopibd.dactarbari.doctor.appointment.Widgets;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.twopibd.dactarbari.doctor.appointment.Activity.AddWeeklyChamberActivity;
import com.twopibd.dactarbari.doctor.appointment.Adapter.TestListAdapter;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.testSelectedModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.selectedTestIds;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.testModelList;


/**
 * Created by mukul on 3/22/2019.
 */

public class MyDialogList {
    private static MyDialogList myDialogList;
    Activity activity;
    testSelectedListener testSelectListener;
    confirmListener cListener;
    ScheduleDialogListener scheduleDialogListener;
    List<testSelectedModel> testModelLists = new ArrayList<>();
    int TYPE_START = 0;
    int TYPE_END = 1;
    Day day = new Day();
    private int mYear, mMonth, mDay, mHour, mMinute;
    TextView tv_start_time;
    TextView tv_end_time;
    int startTime = 0, endTime = 0;
    int capacity = 0;

    public static MyDialogList getInstance() {

        if (myDialogList == null) {
            myDialogList = new MyDialogList();
        }
        return myDialogList;
    }

    public MyDialogList with(Activity activity) {
        this.activity = activity;
        return this;
    }

    public MyDialogList setData(List<testSelectedModel> data) {
        this.testModelLists = data;
        return this;
    }

    public interface testSelectedListener {
        void onDialogCloased(List<String> selectedTest);
    }

    public interface confirmListener {
        void onDialogClicked(boolean result);
    }

    public interface ScheduleDialogListener {
        void onYesDialogClicked(boolean result, Day day);

        void onNoDialogClicked(boolean result);
    }

    public void yesNoConfirmation(confirmListener listener, String message) {
        this.cListener = listener;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.yes_no_dialog);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
        TextView tv_done = (TextView) dialog.findViewById(R.id.tv_done);
        TextView tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        tv_msg.setText(message);

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cListener.onDialogClicked(true);


                dialog.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cListener.onDialogClicked(false);

                dialog.dismiss();
            }
        });


    }

    private void showPicker(TextView textView, int TYPE) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        String formatedTime = formateDate(hourOfDay, minute);
                        if (TYPE == TYPE_START) {
                            day.setStart_time(doubleDigit(hourOfDay) + ":" + doubleDigit(minute));
                            startTime = (hourOfDay * 60) + minute;

                        } else {
                            day.setEnd_time(doubleDigit(hourOfDay) + ":" + doubleDigit(minute));
                            endTime = (hourOfDay * 60) + minute;


                        }


                        textView.setText(doubleDigit(hourOfDay) + ":" + doubleDigit(minute));
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private String formateDate(int hourOfDay, int minute) {
        String newHour = doubleDigit(hourOfDay % 12);
        String newMinute = doubleDigit(minute);
        String am_pm = amPm(hourOfDay);
        if (am_pm.equals("AM") && newHour.equals("00")) {
            newHour = "12";
        }
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

    public void addScheduleDialog(ScheduleDialogListener listener) {
        this.scheduleDialogListener = listener;
        //Day day = new Day();
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.add_schedule_dialog);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        TextView tv_done = (TextView) dialog.findViewById(R.id.tv_done);
        Spinner spinnerDays = (Spinner) dialog.findViewById(R.id.spinnerDays);
        TextView tv_cancel = (TextView) dialog.findViewById(R.id.tv_cancel);
        EditText ed_capacity = (EditText) dialog.findViewById(R.id.ed_capacity);
        tv_start_time = (TextView) dialog.findViewById(R.id.tv_start_time);
        tv_end_time = (TextView) dialog.findViewById(R.id.tv_end_time);
        tv_start_time.setText("Select time");
        tv_end_time.setText("Select time");
        List<String> list = DataStore.sevenDays();


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(activity, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDays.setAdapter(dataAdapter);
        spinnerDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                day.setDay("" + (Integer.parseInt(DataStore.convertDayToNmber(list.get(i))) + 1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        tv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker(tv_start_time, TYPE_START);
            }
        });
        tv_end_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPicker(tv_end_time, TYPE_END);
            }
        });

        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capacity = Integer.parseInt(ed_capacity.getText().toString().trim());
                if (endTime > startTime) {
                    if (((endTime - startTime) % capacity) == 0) {
                        day.setPatient_capacity(ed_capacity.getText().toString().trim());
                        scheduleDialogListener.onYesDialogClicked(true, day);
                        dialog.dismiss();
                    } else {

                        int timeToLeft = ((endTime - startTime) % capacity);
                        int timetoAdd = capacity - timeToLeft;
                        endTime += timetoAdd;
                        int newHour = Integer.parseInt(convertTime(endTime));
                        int newMinute = endTime - (newHour * 60);

                        String formatedTime = formateDate(newHour, newMinute);
                        day.setEnd_time("" + newHour + ":" + doubleDigit(newMinute));

                        tv_end_time.setText("" + newHour + ":" + doubleDigit(newMinute));

                    }
                } else {
                    Toast.makeText(activity, "You cannot select end time less than start time", Toast.LENGTH_SHORT).show();
                }


            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scheduleDialogListener.onNoDialogClicked(false);

                dialog.dismiss();
            }
        });


    }

    public String convertTime(int time) {
        String ti = "";
        int v = time / 60;
        ti = doubleDigit(v);
        return ti;
    }

    public void showTestList(testSelectedListener listener) {
        this.testSelectListener = listener;

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.dialog_with_recycler);
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = (RecyclerView) dialog.findViewById(R.id.recycler_view);
        TextView tv_done = (TextView) dialog.findViewById(R.id.tv_done);
        for (int i = 0; i < testModelList.size(); i++) {
            testModelList.get(i).setSelected(false);

        }

        final TestListAdapter mAdapter = new TestListAdapter(testModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        tv_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String> selectedTest = new ArrayList<>();
                for (int i = 0; i < testModelList.size(); i++) {
                    if (testModelList.get(i).isSelected()) {
                        selectedTest.add(testModelList.get(i).getTestModel().getId());
                    }
                }
                selectedTestIds.clear();
                selectedTestIds.addAll(selectedTest);
                testSelectListener.onDialogCloased(selectedTest);

                dialog.dismiss();
            }
        });


    }
}
