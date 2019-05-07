package com.twopibd.dactarbari.doctor.appointment.Api;

public class Listener_ {
    public static ApiListener.locationReceiveListener LocationReceiveListener;

    public static void setLocationReceiveListener(ApiListener.locationReceiveListener listener) {
        Listener_.LocationReceiveListener = listener;
    }
}
