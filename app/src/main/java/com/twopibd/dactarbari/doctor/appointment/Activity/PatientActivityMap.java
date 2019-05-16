package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.JsonElement;
import com.twopibd.dactarbari.doctor.appointment.Adapter.NearbyDrListAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Adapter.SearchAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.WhatYouAreLookingAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiClient;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Api.Listener_;
import com.twopibd.dactarbari.doctor.appointment.Data.Data;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.DepartmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.DoctorModel;
import com.twopibd.dactarbari.doctor.appointment.Model.FeatureType;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.CustomDrawerButton;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.USER_ID;

public class PatientActivityMap extends GPSOpenActivity implements ApiListener.drSearchListener,
        OnMapReadyCallback,
        ApiListener.locationReceiveListener ,
        ApiListener.DepartmentListDownlaodListener{
    @BindView(R.id.profileImage)
    CircleImageView profileImage;

    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;

    @BindView(R.id.ed_search)
    TextView ed_search;
    @BindView(R.id.tv_logout)
    TextView tv_logout;

    Context context = this;
    CustomDrawerButton customDrawerButton;
    DrawerLayout drawer;
    SessionManager sessionManager;
    String key;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_map);
        sessionManager = new SessionManager(context);
        key = sessionManager.getToken();
        ButterKnife.bind(this);
        profileImage.setOnClickListener((View v) -> profileUpdate());

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        customDrawerButton = (CustomDrawerButton) findViewById(R.id.customDrawer);
        customDrawerButton.setDrawerLayout(drawer);
        customDrawerButton.getDrawerLayout().addDrawerListener(customDrawerButton);
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
        setUpDrayer();
        tv_logout.setOnClickListener((View v) -> logout());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        init_search();
        init_lookingFor();


    }

    private void init_lookingFor() {
        List<FeatureType> list = new ArrayList<>();
        list.add(new FeatureType("Doctor","Book","appointment",R.drawable.doctor_female));
        list.add(new FeatureType("Medicines ","Buy","health product",R.drawable.doctor_female));
        list.add(new FeatureType("Diagonostics","Book health","test",R.drawable.doctor_female));


        WhatYouAreLookingAdapterPatient mAdapter = new WhatYouAreLookingAdapterPatient(list);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

        recycler_view.setAdapter(mAdapter);

    }

    private void init_search() {
        ed_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PatientActivityMap.this, SearchDrActivity.class));
            }
        });
//        ed_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String key = charSequence.toString();
//                if (key.length()>0){
//                    Api.getInstance().SearchByName(key, new ApiListener.drNameSearchListener() {
//                        @Override
//                        public void ondrNameSuccess(List<SearchModel> data) {
//                            SearchAdapterDoctor  mAdapter = new SearchAdapterDoctor(data);
//                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
//                            recycler_viewSearch.setLayoutManager(mLayoutManager);
//                            recycler_viewSearch.setItemAnimator(new DefaultItemAnimator());
//                            //searchDr_recycler.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL));
//
//                            recycler_viewSearch.setAdapter(mAdapter);
//                        }
//
//                        @Override
//                        public void ondrNameFailed(String msg) {
//                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
//
//                        }
//                    });
//
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    private void downloadDr() {
        Api.getInstance().allDoctor(this);
    }

    private void logout() {
        sessionManager.setLoggedIn(false);
        customDrawerButton.changeState();
        startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finishAffinity();


    }

    private void profileUpdate() {
        startActivity(new Intent(context, PatientProfileUpdateActivity.class));
    }

    private void setUpDrayer() {

    }

    @Override
    public void onSearchSuccess(List<SearchModel> data) {

        NearbyDrListAdapterPatient mAdapter = new NearbyDrListAdapterPatient(data);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

        recycler_view.setAdapter(mAdapter);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getChamberLatitude() != null && data.get(i).getChamberLongitude() != null && data.get(i).getChamberAddress() != null) {
                LatLng latLng = new LatLng(Double.parseDouble(data.get(i).getChamberLatitude()), Double.parseDouble(data.get(i).getChamberLongitude()));
                mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.drawable.doctor_color_red)).position(latLng).title(data.get(i).getName()).snippet(data.get(i).getChamberAddress()));

            }
        }


    }


    @Override
    public void onLocationRetriveSuccess(LatLng latLng) {
        Toast.makeText(this, latLng.toString(), Toast.LENGTH_SHORT).show();
        LatLng sydney = new LatLng(-34, 151);
        //  mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        Drawable circleDrawable = getResources().getDrawable(R.drawable.circle_shape);
        BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);
        addOverlay(latLng);
//        mMap.addMarker(new MarkerOptions()
//                .position(latLng)
//                .title("You are here")
//                .anchor(.5f, .5f)
//                .icon(markerIcon)
//        );
        // For showing a move to my location button
        mMap.setMyLocationEnabled(true);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {

                DataStore.selectedLocation = mMap.getCameraPosition().target;
            }
        });
        //downloadDr();
        downloadDepartments();


    }

    private void downloadDepartments() {
        Api.getInstance().downloadDepartments(this);
    }

    @Override
    public void onSearchFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }


    private BitmapDescriptor getMarkerIconFromDrawable(Drawable drawable) {
        Canvas canvas = new Canvas();
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        canvas.setBitmap(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    public void addOverlay(LatLng place) {

        GroundOverlay groundOverlay = mMap.addGroundOverlay(new
                GroundOverlayOptions()
                .position(place, 100)
                .transparency(1f)
                .zIndex(3)
                .image(BitmapDescriptorFactory.fromBitmap(drawableToBitmap(context.getDrawable(R.drawable.map_overlay)))));

        startOverlayAnimation(groundOverlay);
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }

        if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    private void startOverlayAnimation(final GroundOverlay groundOverlay) {

        AnimatorSet animatorSet = new AnimatorSet();

        ValueAnimator vAnimator = ValueAnimator.ofInt(0, 100);
        vAnimator.setRepeatCount(ValueAnimator.INFINITE);
        vAnimator.setRepeatMode(ValueAnimator.RESTART);
        vAnimator.setInterpolator(new LinearInterpolator());
        vAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                final Integer val = (Integer) valueAnimator.getAnimatedValue();
                groundOverlay.setDimensions(val);

            }
        });

        ValueAnimator tAnimator = ValueAnimator.ofFloat(0, 1);
        tAnimator.setRepeatCount(ValueAnimator.INFINITE);
        tAnimator.setRepeatMode(ValueAnimator.RESTART);
        tAnimator.setInterpolator(new LinearInterpolator());
        tAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                Float val = (Float) valueAnimator.getAnimatedValue();
                groundOverlay.setTransparency(val);
            }
        });

        animatorSet.setDuration(3000);
        animatorSet.playTogether(vAnimator, tAnimator);
        animatorSet.start();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera

        Listener_.setLocationReceiveListener(this);

    }


    @Override
    public void onDepartmentLisDownloadSuccess(List<DepartmentModel> data) {
        Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDepartmentLisDownloadFailed(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

    }
}
