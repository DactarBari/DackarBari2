package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.getWeekDays;

public class AddScheduleActivity extends BaseActivity {
    @BindView(R.id.radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.tv_daily_status)
    TextView tv_daily_status;
    @BindView(R.id.tv_startDate)
    TextView tv_startDate;
    @BindView(R.id.linerWeekDays)
    LinearLayout linerWeekDays;
    @BindView(R.id.linerMonthly)
    LinearLayout linerMonthly;
    @BindView(R.id.monthlyRadio2)
    RadioButton monthlyRadio2;
    @BindView(R.id.monthlyRadio1)
    RadioButton monthlyRadio1;
    @BindView(R.id.spinnerMonthly_2_1)
    Spinner spinnerMonthly_2_1;
    @BindView(R.id.spinnerMonthly_2_2)
    Spinner spinnerMonthly_2_2;
    @BindView(R.id.tv_start_time)
    TextView tv_start_time;
    @BindView(R.id.tv_end_date)
    TextView tv_end_date;
    Context context = this;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_schedule);
        ButterKnife.bind(this);


        setMonthlyRadio();
        initTimePickers();
        initDatePicker();


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.radioDaily) {
                    tv_daily_status.setVisibility(View.VISIBLE);
                    linerWeekDays.setVisibility(View.GONE);
                    linerMonthly.setVisibility(View.GONE);

                } else if (i == R.id.radioWeekly) {
                    tv_daily_status.setVisibility(View.GONE);
                    linerWeekDays.setVisibility(View.VISIBLE);
                    linerMonthly.setVisibility(View.GONE);


                } else if (i == R.id.radioMonthly) {
                    tv_daily_status.setVisibility(View.GONE);
                    linerWeekDays.setVisibility(View.GONE);
                    linerMonthly.setVisibility(View.VISIBLE);


                }
            }
        });
    }

    private void initDatePicker() {

        tv_startDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "clickedsfsdfsdf", Toast.LENGTH_SHORT).show();
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(context,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tv_startDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

    }


    private void initTimePickers() {
        tv_start_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String formateTime = getTimeFormated(hourOfDay, minute);

                                tv_start_time.setText(formateTime);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();

            }
        });
        tv_end_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                String formateTime = getTimeFormated(hourOfDay, minute);
                                tv_end_date.setText(formateTime);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();


            }
        });
    }

    private String getTimeFormated(int hourOfDay, int minute) {
        String time = "";
        String am_pm;
        if ((hourOfDay / 12) > 0) {
            am_pm = "PM";
        } else {
            am_pm = "AM";
        }
        String newMinute = "";
        String newHour = String.valueOf(hourOfDay % 12);
        if (minute < 10) {
            newMinute = "0" + minute;

        } else {
            newMinute = "" + minute;
        }
        time = newHour + " : " + newMinute + " " + am_pm;

        return time;
    }

    private String forMateIntToDate(int dayOfMonth, int monthOfYear, int year) {
        String string = "";
        string = "" + dayOfMonth + " " + DataStore.convertToMonth(monthOfYear) + " " + year;
        return string;
    }


    private void showDate(int year, int month, int day) {
        tv_start_time.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }

    private void setMonthlyRadio() {
        init_spinnerMonthly_2_1_();
        init_spinnerMonthly_2_2_();
        monthlyRadio1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    monthlyRadio2.setChecked(false);

                } else {
                    monthlyRadio2.setChecked(true);

                }
            }
        });
        monthlyRadio2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    monthlyRadio1.setChecked(false);

                } else {
                    monthlyRadio1.setChecked(true);

                }
            }
        });
    }

    private void init_spinnerMonthly_2_2_() {

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, getWeekDays());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonthly_2_2.setAdapter(dataAdapter);
    }

    private void init_spinnerMonthly_2_1_() {
        List<String> th = new ArrayList<String>();
        th.add("First");
        th.add("Second");
        th.add("Third");
        th.add("Fourth");
        th.add("Last");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, th);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMonthly_2_1.setAdapter(dataAdapter);

    }


}
