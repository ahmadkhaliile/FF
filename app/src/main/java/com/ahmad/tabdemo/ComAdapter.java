package com.ahmad.tabdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ahmad on 2/17/2016.
 */
public class ComAdapter extends RecyclerView.Adapter<ComAdapter.ViewHolder> {

    public List<ComInfo>Comunities;
    public ComAdapter(List<ComInfo> com){Comunities=com;}
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View comLayout =inflater.inflate(R.layout.com_layout, parent, false);
        ViewHolder holder = new ViewHolder(comLayout);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ComInfo comunity = Comunities.get(position);
        TextView ComName = holder.ComName;
        TextView ComNum = holder.ComNum;
        ComName.setText(comunity.getName());
        ComNum.setText(comunity.getNum()+" Persons");
    }

    @Override
    public int getItemCount() {
        return Comunities.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

       public TextView ComName;
        public TextView ComNum;
        public ViewHolder(View itemView) {
            super(itemView);
            ComName = (TextView)itemView.findViewById(R.id.comName);
            ComNum = (TextView)itemView.findViewById(R.id.comNum);
        }
    }
}
