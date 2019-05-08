package com.twopibd.dactarbari.doctor.appointment.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.twopibd.dactarbari.doctor.appointment.Adapter.SearchAdapterDoctor;
import com.twopibd.dactarbari.doctor.appointment.Api.Api;
import com.twopibd.dactarbari.doctor.appointment.Api.ApiListener;
import com.twopibd.dactarbari.doctor.appointment.Model.SearchModel;
import com.twopibd.dactarbari.doctor.appointment.R;
import com.twopibd.dactarbari.doctor.appointment.Widgets.DividerItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchDrActivity extends AppCompatActivity {
    Context context=this;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    @BindView(R.id.ed_search)
    EditText ed_search;
    SearchAdapterDoctor mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_dr);
        ButterKnife.bind(this);

        init_search();

    }

    private void init_search() {

        ed_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String key = charSequence.toString();
                if (key.length()>0){
                    Api.getInstance().SearchByName(key, new ApiListener.drNameSearchListener() {
                        @Override
                        public void ondrNameSuccess(List<SearchModel> data) {
                             mAdapter = new SearchAdapterDoctor(data);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
                            recycler_view.setLayoutManager(mLayoutManager);
                            recycler_view.setItemAnimator(new DefaultItemAnimator());
                            recycler_view.addItemDecoration(new DividerItemDecoration(context, LinearLayoutManager.VERTICAL, false));


                            recycler_view.setAdapter(mAdapter);
                        }

                        @Override
                        public void ondrNameFailed(String msg) {
                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                    });

                }else {
                    mAdapter.clearAdapter();

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    public void back(View view) {
        onBackPressed();
    }
}
