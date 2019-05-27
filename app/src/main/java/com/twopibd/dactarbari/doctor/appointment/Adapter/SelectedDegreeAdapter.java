package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Model.SelectedHospitalsModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SelectedQualificationModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Activity.DrSignUpActivity.SelectedQualifications;

/**
 * Created by ravi on 16/11/17.
 */

public class SelectedDegreeAdapter extends RecyclerView.Adapter<SelectedDegreeAdapter.MyViewHolder> {
    private Context context;
    private DegreeClickListener listener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        ImageView img_close;


        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            img_close = view.findViewById(R.id.img_close);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onDegreeSelected(SelectedQualifications.get(getAdapterPosition()));
                }
            });
        }
    }


    public SelectedDegreeAdapter(Context context, DegreeClickListener listener) {
        this.context = context;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_value_close, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SelectedQualificationModel data = SelectedQualifications.get(position);
        holder.tv_name.setText(data.getQualificationModel().getName());
        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SelectedQualifications.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,SelectedQualifications.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return SelectedQualifications.size();
    }


    public interface DegreeClickListener {
        void onDegreeSelected(SelectedQualificationModel data);
    }


}