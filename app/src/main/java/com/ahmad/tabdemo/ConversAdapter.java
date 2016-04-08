package com.ahmad.tabdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Ahmad on 2/16/2016.
 */
public class ConversAdapter extends
        RecyclerView.Adapter<ConversAdapter.ViewHolder> {



    // ... view holder defined above...

    // Store a member variable for the contacts
    private List<Convers_Info> mConvs;

    // Pass in the contact array into the constructor
    public ConversAdapter(List<Convers_Info> contacts) {
        mConvs = contacts;
    }


    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ConversAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.activity_conversation_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the data model based on position
        Convers_Info contact = mConvs.get(position);
        TextView NameViewText = holder.nameTextView;
        TextView MessageViewText = holder.messageTextView;
        NameViewText.setText(contact.getName());
        MessageViewText.setText(contact.getMessage());
    }

    // Involves populating data into the item through holder


    // Return the total count of items
    @Override
    public int getItemCount() {
        return mConvs.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public TextView messageTextView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.conName);
            messageTextView = (TextView) itemView.findViewById(R.id.conMsg);
        }
    }
}