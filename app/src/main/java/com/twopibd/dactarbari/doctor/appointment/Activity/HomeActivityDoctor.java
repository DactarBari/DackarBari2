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
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.applikeysolutions.cosmocalendar.dialog.CalendarDialog;
import com.applikeysolutions.cosmocalendar.dialog.OnDaysSelectionListener;
import com.applikeysolutions.cosmocalendar.model.Day;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Adapter.AppointmentSearchDrAdapter;
import com.twopibd.dactarbari.doctor.appointment.Adapter.AssistantListAdapter;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ChamberListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ConfirmedAppointmentAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiClient;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.Constants;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Fragments.HomeFragment;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModels;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentSearchModel;
import com.twopibd.dactarbari.doctor.appointment.Model.AssistantOnlineModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.CustomDrawerButton;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.TOKEN;
import static com.twopibd.dactarbari.doctor.appointment.Data.Data.USER_ID;

public class HomeActivityDoctor extends BaseActivity implements ApiListener.appoinetmentsDownloadListener, ApiListener.MyAssistantsListDownloadListener, SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.v_1)
    View v_1;
    @BindView(R.id.v_2)
    View v_2;
    @BindView(R.id.v_3)
    View v_3;
    @BindView(R.id.v_4)
    View v_4;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipe;

    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.linerProfileBody)
    LinearLayout linerProfileBody;
    @BindView(R.id.linear_home)
    LinearLayout linear_home;
    @BindView(R.id.linear_assistant_)
    LinearLayout linear_assistant_;
    @BindView(R.id.schroll_1)
    ScrollView schroll_1;
    @BindView(R.id.schroll_2)
    ScrollView schroll_2;
    @BindView(R.id.divider)
    View divider;
    @BindView(R.id.ed_search)
    EditText ed_search;
    @BindView(R.id.tv_pending)
    TextView tv_pending;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.recycler_view_assistantList)
    RecyclerView recycler_view_assistantList;
    @BindView(R.id.tv_confirmed)
    TextView tv_confirmed;
    @BindView(R.id.profile_image)
    CircleImageView profile_image;
    SessionManager sessionManager;
    @BindView(R.id.customDrawer)
    CustomDrawerButton customDrawerButton;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;
    Context context = this;
    public static List<AppointmentModels> PENDING_LIST = new ArrayList<>();
    public static List<AppointmentModels> CONFIRMED_LIST = new ArrayList<>();
    int state = 0;
    AppointmentSearchDrAdapter mAdapter;
    List<AppointmentSearchModel> searchModelList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_doctor);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(this);
        USER_ID = sessionManager.getUserId();
        TOKEN = sessionManager.getToken();
        Data.sessionManager = sessionManager;
        init_display();


        customDrawerButton.setDrawerLayout(drawer_layout);
        customDrawerButton.getDrawerLayout().addDrawerListener(customDrawerButton);
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });

        swipe.setOnRefreshListener(this);
        onRefresh();


    }

    private void init_1_0_0_0() {
        v_1.setBackgroundColor(Color.parseColor("#cccccc"));
        //   v_1.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        v_2.setBackgroundColor(Color.WHITE);
        v_3.setBackgroundColor(Color.WHITE);
        v_4.setBackgroundColor(Color.WHITE);
    }

    private void init_0_1_0_0() {
        v_1.setBackgroundColor(Color.WHITE);
        v_2.setBackgroundColor(Color.parseColor("#cccccc"));
        // v_2.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        v_3.setBackgroundColor(Color.WHITE);
        v_4.setBackgroundColor(Color.WHITE);

    }

    private void init_navigation() {
        linear_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init_1();
                linear_home.setAlpha(1);
                linear_assistant_.setAlpha(0.5f);
                init_1_0_0_0();

            }
        });
        linear_assistant_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init_2();
                linear_assistant_.setAlpha(1);
                linear_home.setAlpha(0.5f);
                init_0_1_0_0();

            }
        });
    }

    private void init_1() {
        schroll_1.setVisibility(View.VISIBLE);
        schroll_2.setVisibility(View.GONE);
        linerProfileBody.setVisibility(View.VISIBLE);
    }

    private void init_2() {
        schroll_1.setVisibility(View.GONE);
        schroll_2.setVisibility(View.VISIBLE);
        linerProfileBody.setVisibility(View.GONE);

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
                    Api.getInstance().track(TOKEN, track_id, USER_ID, new ApiListener.drTrackIdListener() {
                        @Override
                        public void onTrackIdSuccess(List<AppointmentSearchModel> data) {
                            //Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();

                            if (data.size() > 0) {
                                searchModelList.clear();
                                searchModelList.addAll(data);
                                mAdapter.notifyDataSetChanged();
                                divider.setVisibility(View.VISIBLE);
                            } else {
                                divider.setVisibility(View.VISIBLE);
                                searchModelList.clear();
                                AppointmentSearchModel model = new AppointmentSearchModel();
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
        if (data != null && state == 0) {
            PENDING_LIST = data;
            state++;
            tv_pending.setText("" + data.size());
            Api.getInstance().getAppointmentsByDoctor(TOKEN, USER_ID, "doctor", "1", this);

        } else if (data != null && state == 1) {
            tv_confirmed.setText("" + data.size());
            CONFIRMED_LIST = data;
            state = 0;
        } else if (data == null) {
            tv_pending.setText("0");
            tv_confirmed.setText("0");

        }

    }

    @Override
    public void onAppointmentDownloadFailed(String msg) {
        Toast.makeText(context, "here 1" + msg, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAssistantsListDownloadSuccess(List<AssistantOnlineModel> data) {
        swipe.setRefreshing(false);

        if (data != null) {
            AssistantListAdapter mAdapter = new AssistantListAdapter(data);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            StaggeredGridLayoutManager _sGridLayoutManager = new StaggeredGridLayoutManager(2,
                    StaggeredGridLayoutManager.VERTICAL);
            recycler_view_assistantList.setLayoutManager(mLayoutManager);
            recycler_view_assistantList.setItemAnimator(new DefaultItemAnimator());
            //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

            recycler_view_assistantList.setAdapter(mAdapter);
        } else Toast.makeText(context, "Assistant download failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAssistantsListDownloadFailed(String msg) {
        swipe.setRefreshing(false);
        Toast.makeText(context,
                "Assistant download failed", Toast.LENGTH_SHORT).show();

    }

    public void createAssistantActivity(View view) {
        startActivity(new Intent(this, CreateAssistantActivity.class));
    }

    @Override
    public void onRefresh() {
        Api.getInstance().getAppointmentsByDoctor(TOKEN, USER_ID, "doctor", "0", this);
        initRecycler();
        init_search();
        init_1();
        init_navigation();
        Api.getInstance().getMyAssistantsList(TOKEN, USER_ID, this);
        init_1_0_0_0();

    }
}
