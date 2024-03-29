package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Activity.ServingActivityDr;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentSearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.selectedSearchAppointmentModel;


/**
 * Created by mukul on 3/10/2019.
 */


public class AppointmentSearchDrAdapter extends RecyclerView.Adapter<AppointmentSearchDrAdapter.MyViewHolder> {
    List<AppointmentSearchModel> list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_refID,tv_name,tv_noResult;
        ImageView circleImageView;
        RelativeLayout relative_container;
        LinearLayout lin1,lin2;


        public MyViewHolder(View view) {
            super(view);
            tv_refID = (TextView) view.findViewById(R.id.tv_refID);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_noResult = (TextView) view.findViewById(R.id.tv_noResult);
            lin1 = (LinearLayout) view.findViewById(R.id.lin1);
            lin2 = (LinearLayout) view.findViewById(R.id.lin2);



        }
    }


    public AppointmentSearchDrAdapter(List<AppointmentSearchModel> lists ) {
        this.list=lists;

    }
    public  void clearData(){
        list.clear();
        notifyDataSetChanged();

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.appointment_search_id, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AppointmentSearchModel data = list.get(position);
        context = holder.tv_refID.getContext();

        if (data.getId()==0) {
            holder.tv_noResult.setVisibility(View.VISIBLE);
            holder.lin2.setVisibility(View.GONE);
            holder.lin1.setVisibility(View.GONE);
        }else {
            holder.tv_refID.setText("" + data.getId());
            holder.tv_name.setText(data.getAppointmentFor());
            holder.tv_noResult.setVisibility(View.GONE);

            holder.lin2.setVisibility(View.VISIBLE);
            holder.lin1.setVisibility(View.VISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedSearchAppointmentModel=data;
                context.startActivity(new Intent(context, ServingActivityDr.class));

            }
        });


    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}