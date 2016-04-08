package com.ahmad.tabdemo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class PostsAdapter extends
        RecyclerView.Adapter<PostsAdapter.ViewHolder> {



    // ... view holder defined above...

    // Store a member variable for the contacts
    private ArrayList<Post_information> mContacts;

    // Pass in the contact array into the constructor
    public PostsAdapter(ArrayList contacts) {
        mContacts = contacts;
    }

Context c;
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        c = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.post_layout, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(PostsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        final Post_information contact = mContacts.get(position);

        // Set item views based on the data model
        TextView postText = viewHolder.textTextView;
        TextView postName = viewHolder.nameTextView;
        TextView Date = viewHolder.date;
        TextView time = viewHolder.time;
        ImageView postImg = viewHolder.postImage;
        postImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(c, contact.getUserID(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Profile.Profile,Add_Post.class);
                Profile.Profile.startActivity(intent);
            }
        });
        postText.setText(contact.getmText());
        postName.setText(contact.getmName());
        Picasso.with(c)
                .load(contact.getImgUrl())
                .resize(40, 40)
                .centerCrop()
                .into(postImg);


        //DateTime

        String datetime = contact.getDateTime();
        String date ="";
        String timee="";
        for(int i=0;i<datetime.length();i++){
            if(i<=10){
                date = date + datetime.charAt(i);
            }

        }
        Date.setText(date);

    }


    // Return the total count of items
    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textTextView;
        public TextView nameTextView;
        public ImageView postImage;
        public TextView date;
        public TextView time;
        public ViewHolder(View itemView) {

            super(itemView);

            textTextView = (TextView) itemView.findViewById(R.id.post_text);
            nameTextView = (TextView) itemView.findViewById(R.id.postName);
            date = (TextView)itemView.findViewById(R.id.date);
            time = (TextView)itemView.findViewById(R.id.number);
            postImage = (ImageView) itemView.findViewById(R.id.userPic);
        }
    }
}