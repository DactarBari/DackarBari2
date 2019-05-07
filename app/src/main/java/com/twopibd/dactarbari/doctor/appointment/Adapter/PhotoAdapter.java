package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.photoModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;


/**
 * Created by mukul on 3/10/2019.
 */


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,imageDlt;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            imageDlt = (ImageView) view.findViewById(R.id.imageDlt);



        }
    }


    public PhotoAdapter( ) {

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.photo_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final photoModel data = photoModelList.get(position);
        context = holder.imageView.getContext();
        Glide.with(context).load(data.getPhotoLink()).into(holder.imageView);
        holder.imageDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                photoModelList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,photoModelList.size());
            }
        });




    }



    @Override
    public int getItemCount() {
        return photoModelList.size();
    }
}