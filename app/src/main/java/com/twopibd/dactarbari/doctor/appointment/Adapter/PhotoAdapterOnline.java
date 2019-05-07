package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.twopibd.dactarbari.doctor.appointment.Activity.LoginActivity;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.PrescriptionInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.Model.photoModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.MyDialog;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyDialogList;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.PHOTO_BASE;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;


/**
 * Created by mukul on 3/10/2019.
 */


public class PhotoAdapterOnline extends RecyclerView.Adapter<PhotoAdapterOnline.MyViewHolder> {

    Context context;
    List<PrescriptionInfo> list = new ArrayList<>();


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageDlt;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            imageDlt = (ImageView) view.findViewById(R.id.imageDlt);


        }
    }


    public PhotoAdapterOnline(List<PrescriptionInfo> prescriptionInfo) {
        this.list = prescriptionInfo;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final PrescriptionInfo data = list.get(position);
        context = holder.imageView.getContext();
        String path = PHOTO_BASE + data.getPrescription();
        if (path.contains("/storage/emulated")) {
            Glide.with(context).load(data.getPrescription()).into(holder.imageView);
        } else {
            Glide.with(context).load(path).into(holder.imageView);


        }
        //  Glide.with(context).load(data.getPrescription()).into(holder.imageView);

        // Glide.with(context).load(path).into(holder.imageView);
        holder.imageDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                photoModelList.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position,photoModelList.size());
                String key = new SessionManager(context).getToken();
                MyDialogList.getInstance().with((Activity) context).yesNoConfirmation(new MyDialogList.confirmListener() {
                    @Override
                    public void onDialogClicked(boolean result) {
                        if (result) {
                            if (!path.contains("/storage/emulated")) {


                                MyProgressDialog.with(context);
                                Api.getInstance().deletePrescription(key, "" + data.getId(), new ApiListener.deletePrescriptionListener() {
                                    @Override
                                    public void onPrescriptionDeleteSuccess(StatusMessage response) {
                                        MyProgressDialog.destroy();

                                        if (response.getStatus()) {
                                            list.remove(position);
                                            notifyItemRemoved(position);
                                            notifyItemRangeChanged(position, list.size());
                                            MyDialog.getInstance().with((Activity) context)
                                                    .message(response.getMessage())
                                                    .autoBack(false)
                                                    .autoDismiss(false)
                                                    .show();
                                        } else {
                                            MyDialog.getInstance().with((Activity) context)
                                                    .message(response.getMessage())
                                                    .autoBack(false)
                                                    .autoDismiss(false)
                                                    .show();
                                        }

                                    }

                                    @Override
                                    public void onPrescriptionDeleteFailed(String msg) {
                                        MyProgressDialog.destroy();

                                        MyDialog.getInstance().with((Activity) context)
                                                .message("API Error " + msg)
                                                .autoBack(false)
                                                .autoDismiss(false)
                                                .show();

                                    }
                                });
                            } else {
                                list.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, list.size());
                            }

                        } else {
                            Toast.makeText(context, "No clicked", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, "Do you really want to delete this prescription?");
            }
        });


    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}