package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.squareup.picasso.Picasso;
import com.twopibd.dactarbari.doctor.appointment.Activity.ChamberDetailActivity;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.twopibd.dactarbari.doctor.appointment.Data.Constants.PHOTO_BASE;

/**
 * Created by mukul on 3/10/2019.
 */


public class SearchAdapterDoctor extends RecyclerView.Adapter<SearchAdapterDoctor.MyViewHolder> {
    List<SearchModel>list=new ArrayList<>();

    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name, tv_department, tv_time, tv_lastDegree, tv_epacialist,tv_address,tv_serial;
        CircleImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_name = (TextView) view.findViewById(R.id.tv_name);
            tv_department = (TextView) view.findViewById(R.id.tv_department);
            circleImageView = (CircleImageView) view.findViewById(R.id.profile_image);


        }
    }
    public  void clearAdapter(){
        list.clear();
        notifyDataSetChanged();


    }


    public SearchAdapterDoctor(List<SearchModel> lists ) {
        this.list=lists;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dr_search_item_, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final SearchModel movie = list.get(position);
        context = holder.tv_name.getContext();
        holder.tv_name.setText(movie.getName());
        holder.tv_department.setText(movie.getDepartmentName());
        Picasso.with(context).load(PHOTO_BASE+movie.getPhoto()).into(holder.circleImageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataStore.selectedSearchModel=movie;
                context.startActivity(new Intent(context, ChamberDetailActivity.class));


            }
        });

//        String time = "";
//        for (int i = 0; i < movie.getDays().size(); i++) {
//            time += movie.getDays().get(i).getDay() + "  " + movie.getDays().get(i).getTime() + "\n";
//        }
//        holder.tv_time.setText(time);

    }



    @Override
    public int getItemCount() {
        return list.size();
    }
}