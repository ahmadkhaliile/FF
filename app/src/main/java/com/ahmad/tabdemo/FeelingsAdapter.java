package com.ahmad.tabdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ahmad on 2/1/2016.
 */
class FeelingsAdapter extends ArrayAdapter<String> {
    public FeelingsAdapter(Context context, String [] resource) {
        super(context,R.layout.feelings_layout, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.feelings_layout, parent, false);

        String singleFeel = getItem(position);
        TextView text=(TextView)customView.findViewById(R.id.feel);
        text.setText(singleFeel);
        return customView;
    }
}
