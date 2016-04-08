package com.ahmad.tabdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.beardedhen.androidbootstrap.TypefaceProvider;


public class Feelings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.activity_feelings);

        String feels[] = {"Sad","Happy","Hopeful","Amused","Sick","Sad","Happy","Hopeful","Amused","Sick","Sad","Happy","Hopeful","Amused","Sick","Sad","Happy","Hopeful","Amused","Sick"};
        ListAdapter fellAdapter = new FeelingsAdapter(this,feels);
        ListView feelings = (ListView)findViewById(R.id.feelings);
        feelings.setAdapter(fellAdapter);
        }


    }

