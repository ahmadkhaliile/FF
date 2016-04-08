package com.ahmad.tabdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Profile_Top extends Fragment {


    TextView profileName;
    static String Name;

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile__top,container,false);
        profileName = (TextView)view.findViewById(R.id.profileName);
        if(savedInstanceState != null){
            Name = savedInstanceState.getString("name");

            profileName.setText(Name);
        }else{
          Name=getName();
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   getData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name",Name);
    }


    public static void setName(String name) {
        Name = name;
    }

    public  String getName() {
        return Name;
    }
}