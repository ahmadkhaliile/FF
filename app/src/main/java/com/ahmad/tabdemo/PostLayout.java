package com.ahmad.tabdemo;

import android.app.Application;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Ahmad on 2/6/2016.
 */
public class PostLayout extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.post_layout);

        ImageView img = (ImageView)findViewById(R.id.userPic);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Hey!!!",Toast.LENGTH_LONG).show();
                Log.d("j","k");
            }
        });
    }
}
