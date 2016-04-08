package com.ahmad.tabdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.beardedhen.androidbootstrap.BootstrapCircleThumbnail;

public class Notifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerNotifications);
        NotiAdapter adapter = new NotiAdapter(Noti_Info.CreateNoti());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
