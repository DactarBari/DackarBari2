package com.twopibd.dactarbari.doctor.appointment.Data;

import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;

public class locationPickListener {
    public static ApiListener.LocationPicked locationPicked;

    public static void setlocationPickerListener(ApiListener.LocationPicked Picked) {
        locationPicked = Picked;

    }


}
