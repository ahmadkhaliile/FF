package com.ahmad.tabdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.util.Timer;
import java.util.TimerTask;

public class Navigate extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigate);

    }


    public void profileIntent(View v){

        Intent fuck = new Intent(getBaseContext(),Profile.class);
        //  overridePendingTransition(0,0);
        startActivity(fuck);
    }
    public void convsIntent(View v){

        Intent fuck = new Intent(getBaseContext(),Conversations.class);
        //  overridePendingTransition(0,0);
        startActivity(fuck);
    }
    public void settingsIntent(View v){

        Intent fuck = new Intent(getBaseContext(),Settings.class);
        //  overridePendingTransition(0,0);
        startActivity(fuck);
    }
    public void notiIntent(View v){

        Intent fuck = new Intent(getBaseContext(),Notifications.class);
        //  overridePendingTransition(0,0);
        startActivity(fuck);
    }
    public void comIntent(View v){

        Intent fuck = new Intent(getBaseContext(),Comunities.class);
        //  overridePendingTransition(0,0);
        startActivity(fuck);
    }

    

}
