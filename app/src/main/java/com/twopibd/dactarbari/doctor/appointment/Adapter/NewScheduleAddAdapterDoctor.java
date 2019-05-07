package com.twopibd.dactarbari.doctor.appointment.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Data.DataStore;
import com.twopibd.dactarbari.doctor.appointment.Model.AppointmentModel;
import com.twopibd.dactarbari.doctor.appointment.Model.ChamberModel;
import com.twopibd.dactarbari.doctor.appointment.Model.Day;
import com.twopibd.dactarbari.doctor.appointment.Model.ScheduleInfo;
import com.twopibd.dactarbari.doctor.appointment.Model.StatusMessage;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Utils.SessionManager;
import com.twopibd.dactarbari.doctor.appointment.Widgets.MyProgressDialog;

import java.util.ArrayList;
import java.util.List;

import static com.twopibd.dactarbari.doctor.appointment.Data.Data.newSchedulelist;
import static com.twopibd.dactarbari.doctor.appointment.Data.DataStore.photoModelList;


/**
 * Created by mukul on 3/10/2019.
 */


public class NewScheduleAddAdapterDoctor extends RecyclerView.Adapter<NewScheduleAddAdapterDoctor.MyViewHolder> {


    Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_day, tv_start, tv_end, tv_capacity;
        ImageView circleImageView;
        RelativeLayout relative_container;


        public MyViewHolder(View view) {
            super(view);
            tv_day = (TextView) view.findViewById(R.id.tv_day);
            tv_start = (TextView) view.findViewById(R.id.tv_start);
            tv_end = (TextView) view.findViewById(R.id.tv_end);
            tv_capacity = (TextView) view.findViewById(R.id.tv_capacity);


        }
    }


    public NewScheduleAddAdapterDoctor(Context con) {
        this.context=con;


    }
    public void addToAdapter(Day data ) {
        newSchedulelist.add(data);
        notifyItemInserted(newSchedulelist.size()-1);
        Gson gson=new Gson();
      //  Toast.makeText(context, gson.toJson(newSchedulelist), Toast.LENGTH_LONG).show();

    }
    public  List<Day> getList(){
        return newSchedulelist;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chamber_days_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Day movie = newSchedulelist.get(position);
        context = holder.tv_day.getContext();
        SessionManager sessionManager=new SessionManager(context);
        String key=sessionManager.getToken();
        holder.tv_day.setText(DataStore.convertToWeekDay(""+(Integer.parseInt(movie.getDay())-1)));
        holder.tv_start.setText(movie.getStart_time());
        holder.tv_end.setText(movie.getEnd_time());
        holder.tv_capacity.setText(""+movie.getPatient_capacity());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // list.remove(position);
              //  notifyItemRemoved(position);
               // notifyItemRangeChanged(position,list.size());
               // notifyDataSetChanged();
               // Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

            }
        });



    }



    @Override
    public int getItemCount() {
        return newSchedulelist.size();
    }
}