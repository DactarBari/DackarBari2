package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Adapter.AppointmentSearchDrAdapter;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ConfirmedAppointmentAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Fragments.HomeFragment;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentSearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivityDoctor extends AppCompatActivity implements ApiListener.appoinetmentsDownloadListener {
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.tv_pending)
    TextView tv_pending;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_confirmed)
    TextView tv_confirmed;
    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    SessionManager sessionManager;
    String USER_ID, key;
    Context context = this;
    public static List<AppointmentModels> PENDING_LIST = new ArrayList<>();
    public static List<AppointmentModels> CONFIRMED_LIST = new ArrayList<>();
    int state = 0;
    String TOKEN;
    AppointmentSearchDrAdapter mAdapter;
    List<AppointmentSearchModel> searchModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        Toast.makeText(this, sessionManager.getUserId(), Toast.LENGTH_SHORT).show();
        init_display();
        USER_ID = sessionManager.getUserId();
        key = sessionManager.getToken();
        Api.getInstance().getAppointmentsByDoctor(key, USER_ID, "doctor", "0", this);
        initRecycler();
        init_search();


    }

    private void initRecycler() {
        mAdapter = new AppointmentSearchDrAdapter(searchModelList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));
        recycler_view.setAdapter(mAdapter);
    }

    private void init_search() {
        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String track_id = charSequence.toString();
                if (track_id.length() > 0) {
                    Api.getInstance().track(key, track_id, new ApiListener.drTrackIdListener() {
                        @Override
                        public void onTrackIdSuccess(List<AppointmentSearchModel> data) {
                            Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();

                            if (data.size() > 0) {
                                searchModelList.clear();
                                searchModelList.addAll(data);
                                mAdapter.notifyDataSetChanged();
                                divider.setVisibility(View.VISIBLE);
                            } else {
                               divider.setVisibility(View.VISIBLE);
                                searchModelList.clear();
                                AppointmentSearchModel model=new AppointmentSearchModel();
                                model.setId(0);
                                model.setAppointmentFor("No result found");
                                searchModelList.add(model);
                                mAdapter.notifyDataSetChanged();



                            }
                        }

                        @Override
                        public void onTrackIdFailed(String msg) {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                    });
                } else {
                    divider.setVisibility(View.GONE);
                    if (mAdapter != null) {
                        searchModelList.clear();
                        mAdapter.notifyDataSetChanged();
                    }
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public static void setLightStatusBar(View view, Activity activity) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            activity.getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    private void init_display() {
        tv_name.setText(sessionManager.getUserName());
        Picasso.with(HomeActivityDoctor.this).load(Constants.PHOTO_BASE + sessionManager.get_userPhoto()).into(profile_image);
        profile_image.setOnClickListener((View v) -> openDrPersonalInfoActivity());
    }

    private void openDrPersonalInfoActivity() {
        startActivity(new Intent(this, DrPersonalInfoActivity.class));
        finish();

    }

    public void openScheduleDr(View view) {
        startActivity(new Intent(this, ScheduleMenuActivity.class));
    }


    public void OpenProfile(View view) {
        openDrPersonalInfoActivity();
    }

    public void logout(View view) {
        sessionManager.setLoggedIn(false);
        startActivity(new Intent(this, LoginActivity.class));
        finishAffinity();
    }

    public void pendingActivity(View view) {
        startActivity(new Intent(this, DrPendingAppointments.class));
    }

    public void openConfirmed(View view) {
        startActivity(new Intent(this, DoctorConfirmedActivity.class));

    }

    @Override
    public void onAppointmentDownloadSuccess(List<AppointmentModels> data) {
        if (state == 0) {
            PENDING_LIST = data;
            state++;
            tv_pending.setText("" + data.size());
            Api.getInstance().getAppointmentsByDoctor(key, USER_ID, "doctor", "1", this);

        } else if (state == 1) {
            tv_confirmed.setText("" + data.size());
            CONFIRMED_LIST = data;
            state = 0;
        }

    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
