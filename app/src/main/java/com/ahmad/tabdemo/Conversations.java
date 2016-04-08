package com.ahmad.tabdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class Conversations extends AppCompatActivity {

    RecyclerView conversations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversations);
        conversations = (RecyclerView)findViewById(R.id.conversations);
        ConversAdapter adapter = new ConversAdapter(Convers_Info.createNameList());
        conversations.setAdapter(adapter);
        conversations.setLayoutManager(new LinearLayoutManager(this));
    }
}
