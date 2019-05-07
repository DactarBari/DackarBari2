package com.twopibd.dactarbari.doctor.appointment.Fragments;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
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
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.twopibd.dactarbari.doctor.appointment.Activity.LoginActivity;
import com.twopibd.dactarbari.doctor.appointment.Activity.PatientProfileUpdateActivity;
import com.twopibd.dactarbari.doctor.appointment.Adapter.ChamberListAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Adapter.NearbyDrListAdapterPatient;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Api.Listener_;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.CustomDrawerButton;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment implements ApiListener.drSearchListener{
    View v;
    @BindView(R.id.profileImage)
    CircleImageView profileImage;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.tv_logout)
    TextView tv_logout;
    Context context;
    CustomDrawerButton customDrawerButton;
    DrawerLayout drawer;
    SessionManager sessionManager;
    String key;
    MapView mMapView;
    private GoogleMap googleMap;
    //-----

    //-----
    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v= inflater.inflate(R.layout.fragment_home, container, false);
        context=getContext();
        sessionManager=new SessionManager(context);
        key=sessionManager.getToken();
        ButterKnife.bind(this,v);
        profileImage.setOnClickListener((View v)->profileUpdate());

        drawer= (DrawerLayout)v.findViewById(R.id.drawer_layout);
        customDrawerButton = (CustomDrawerButton)v.findViewById(R.id.customDrawer);
        customDrawerButton.setDrawerLayout( drawer );
        customDrawerButton.getDrawerLayout().addDrawerListener( customDrawerButton );
        customDrawerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDrawerButton.changeState();
            }
        });
        setUpDrayer();
        tv_logout.setOnClickListener((View v)->logout());

        mMapView = (MapView) v.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                Listener_.setLocationReceiveListener(new ApiListener.locationReceiveListener() {
                    @Override
                    public void onLocationRetriveSuccess(LatLng latLng) {
                        Toast.makeText(context, latLng.toString(), Toast.LENGTH_SHORT).show();
                        LatLng sydney = new LatLng(-34, 151);
                        //  mMap.addMarker(new MarkerOptions().position(latLng).title("Marker in Sydney"));
                        //  mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                        Drawable circleDrawable = getResources().getDrawable(R.drawable.circle_shape);
                        BitmapDescriptor markerIcon = getMarkerIconFromDrawable(circleDrawable);
                        addOverlay(latLng);
                        downloadDr();
//                        googleMap.addMarker(new MarkerOptions()
//                                .position(latLng)
//                                .title("You are here")
//                                .anchor(.5f, .5f)
//                                .icon(markerIcon)
//                        );
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
                        googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                            @Override
                            public void onCameraIdle() {

                                //  DataStore.selectedLocation=googleMap.getCameraPosition().target;
                            }
                        });
                    }
                });
                googleMap = mMap;

                // For showing a move to my location button
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setZoomControlsEnabled(true);

                // For dropping a marker at a point on the Map
//                LatLng sydney = new LatLng(-34, 151);
//                googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));
//
//                // For zooming automatically to the location of the marker
//                CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
//                googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    private void setMap() {




    }

    private void downloadDr() {
        Api.getInstance().searchDoctor(this);
    }

    private void logout() {
        sessionManager.setLoggedIn(false);
        customDrawerButton.changeState();
        startActivity(new Intent(context, LoginActivity.class));
        ((Activity)context).finishAffinity();


    }

    private void profileUpdate() {
        startActivity(new Intent(context, PatientProfileUpdateActivity.class));
    }

    private void setUpDrayer() {

    }

    @Override
    public void onSearchSuccess(List<SearchModel> data) {
        Toast.makeText(context, ""+data.size(), Toast.LENGTH_SHORT).show();

        NearbyDrListAdapterPatient mAdapter = new NearbyDrListAdapterPatient(data);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        //recycler_view.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL,false));

        recycler_view.setAdapter(mAdapter);

        for (int i=0;i<data.size();i++){
            if (data.get(i).getChamberLatitude()!=null && data.get(i).getChamberLongitude()!=null &&data.get(i).getChamberAddress()!=null){
                LatLng latLng=new LatLng(Double.parseDouble(data.get(i).getChamberLatitude()),Double.parseDouble(data.get(i).getChamberLongitude()));
               googleMap.addMarker(new MarkerOptions().position(latLng).title(data.get(i).getName()).snippet(data.get(i).getChamberAddress()));

            }
        }



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

        GroundOverlay groundOverlay = googleMap.addGroundOverlay(new
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
}