package com.ahmad.tabdemo;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class Followers extends Fragment {

    ArrayList<Followersinfo> list = new ArrayList<>();
    public Followers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View g = inflater.inflate(R.layout.fragment_followers,container,false);
            if(savedInstanceState!=null){
                list = savedInstanceState.getParcelableArrayList("followers");
            }else{list = getArguments().getParcelableArrayList("followerss");}
            try{
            RecyclerView recyclerView = (RecyclerView)g.findViewById(R.id.recyclerFollowers);
            FollowersAdapter adapter = new FollowersAdapter(list,getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            }catch (Exception e){e.printStackTrace();}
        return g;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("followers",getArguments().getParcelableArrayList("followerss"));
    }

    public static Followers newInstance(ArrayList<Followersinfo> followers) {
        Followers f = new Followers();
        Bundle b = new Bundle();
        b.putParcelableArrayList("followerss", followers);
        f.setArguments(b);
        return f;
    }


}
