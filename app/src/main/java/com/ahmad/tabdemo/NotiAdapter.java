package com.ahmad.tabdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmad on 2/17/2016.
 */
public class NotiAdapter extends RecyclerView.Adapter<NotiAdapter.ViewHolder> {

    private List<Noti_Info> notifications;
    public NotiAdapter(List<Noti_Info> nNoti){
        notifications=nNoti;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View NotiLayout = layoutInflater.inflate(R.layout.notification_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(NotiLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Noti_Info notifaction  = notifications.get(position);
        TextView NameViewText = holder.nameTextView;
        TextView MessageViewText = holder.textTextView;
        LinearLayout seen = holder.Seen;
        NameViewText.setText(notifaction.getName());
        MessageViewText.setText(Html.fromHtml(notifaction.getText()));
        if(notifaction.getSeen()== false){
            seen.setVisibility(View.VISIBLE);
        }else{
            seen.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameTextView;
        public TextView textTextView;
        public LinearLayout Seen;
        public ViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView)itemView.findViewById(R.id.notName);
            textTextView = (TextView)itemView.findViewById(R.id.notText);
            Seen=(LinearLayout)itemView.findViewById(R.id.seen);
        }
    }
}
