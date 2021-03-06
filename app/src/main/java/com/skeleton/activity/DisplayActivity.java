package com.skeleton.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.skeleton.R;
import com.skeleton.adapter.DataAdapter;
import com.skeleton.model.UserInfo;
import com.skeleton.retrofit.APIError;
import com.skeleton.retrofit.ResponseResolver;
import com.skeleton.retrofit.RestClient;

import java.util.List;

/**
 * display activity
 */

public class DisplayActivity extends AppCompatActivity {
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        RestClient.getApiInterface().getUserData().enqueue(new ResponseResolver<List<UserInfo>>(this, true) {
            @Override
            public void success(final List<UserInfo> userInfos) {
                DataAdapter recyclerViewAdapter = new DataAdapter(userInfos, DisplayActivity.this);

                recyclerView = (RecyclerView) findViewById(R.id.rv_display);
                recyclerView.setLayoutManager(new LinearLayoutManager(DisplayActivity.this));
                recyclerView.setAdapter(recyclerViewAdapter);

            }

            @Override
            public void failure(final APIError error) {
                Toast.makeText(DisplayActivity.this, "failed!", Toast.LENGTH_LONG).show();


            }
        });
    }

}
