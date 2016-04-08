package com.ahmad.tabdemo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/**
 * Created by Ahmad on 2/3/2016.
 */
public class ActionBar_style extends AppCompatActivity {


   public ActionBar_style(){


   }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);

        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.search:
                Log.d("Clicked", "balls");
                //setContentView(R.layout.activity_profile);
                Intent intent = new Intent(getBaseContext(),Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                return true;
            case R.id.settings:
                Log.d("Clicked","bally");
                item.setIcon(R.mipmap.settings_filled);
                setContentView(R.layout.activity_main);
                return true;
            case R.id.menu:
                Log.d("Clicked","fuck");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setActionBar(){

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setTitle("Main");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }
}
