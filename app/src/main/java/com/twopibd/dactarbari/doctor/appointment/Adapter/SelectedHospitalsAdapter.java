package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.view.menu.ActionMenuItemView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;


import com.twopibd.dactarbari.doctor.appointment.Activity.DrSignUpActivity;
import com.twopibd.dactarbari.doctor.appointment.Model.HospitalModel;
import com.twopibd.dactarbari.doctor.appointment.Model.SelectedHospitalsModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 16/11/17.
 */

public class SelectedHospitalsAdapter extends RecyclerView.Adapter<SelectedHospitalsAdapter.MyViewHolder> {
    private Context context;
    private List<SelectedHospitalsModel> list=new ArrayList<>();
    private HospitalClickListener listener;


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
                    listener.onHospitalSelected(list.get(getAdapterPosition()));
                }
            });
        }
    }


    public SelectedHospitalsAdapter(Context context, List<SelectedHospitalsModel> contactList, HospitalClickListener listener) {
        this.context = context;
        this.listener = listener;
        this.list = contactList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_value_close, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SelectedHospitalsModel data = list.get(position);
        holder.tv_name.setText(data.getHospitalModel().getName());
        holder.img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,list.size());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public interface HospitalClickListener {
        void onHospitalSelected(SelectedHospitalsModel data);
    }


}