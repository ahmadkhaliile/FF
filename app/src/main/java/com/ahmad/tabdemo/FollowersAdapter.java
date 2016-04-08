package com.ahmad.tabdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad on 2/19/2016.
 */
public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.ViewHolder> {


    ArrayList<Followersinfo> followers;
    Context c;
    public FollowersAdapter(ArrayList<Followersinfo>followers,Context c) {
        this.followers = followers;
        this.c = c;
    }
    public FollowersAdapter() {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View layout = inflater.inflate(R.layout.activity_followers__layout, parent, false);
        ViewHolder holder = new ViewHolder(layout);
        return  holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Followersinfo followersinfo = followers.get(position);
        final TextView FolName = holder.FolName;
        FolName.setText(followersinfo.getName());

        FolName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, OtherProfile.class);
                intent.putExtra("OtherId", followersinfo.getId());

                c.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return  followers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView FolName;

        public ViewHolder(View itemView) {
            super(itemView);

            FolName=(TextView)itemView.findViewById(R.id.folName);
        }
    }
}
