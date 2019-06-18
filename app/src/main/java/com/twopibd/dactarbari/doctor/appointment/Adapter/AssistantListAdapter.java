package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.twopibd.dactarbari.doctor.appointment.Model.AssistantOnlineModel;
import com.twopibd.dactarbari.doctor.appointment.Model.testSelectedModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mukul on 3/10/2019.
 */


public class AssistantListAdapter extends RecyclerView.Adapter<AssistantListAdapter.MyViewHolder> {

    Context context;
    List<AssistantOnlineModel> list = new ArrayList<>();

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name,tv_email,tv_phone,tv_designation;



        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            tv_email = view.findViewById(R.id.tv_email);
            tv_phone = view.findViewById(R.id.tv_phone);



        }
    }


    public AssistantListAdapter(List<AssistantOnlineModel> li) {
        this.list = li;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.assistant_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final AssistantOnlineModel movie = list.get(position);
        holder.tv_name.setText(movie.getAssistantProfile().getName());
        holder.tv_email.setText(movie.getAssistantProfile().getEmail());
        holder.tv_phone.setText(movie.getAssistantProfile().getMobile());



    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}