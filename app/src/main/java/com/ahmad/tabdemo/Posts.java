package com.ahmad.tabdemo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class Posts extends Fragment {
    //static SharedPreferences sharedPreferences;

    public Posts() {
        // Required empty public constructor
    }

   public static ArrayList<Post_information> posts = new ArrayList();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View g = inflater.inflate(R.layout.fragment_posts, container, false);

                posts = getArguments().getParcelableArrayList("msg");
                RecyclerView recyclerView = (RecyclerView)g.findViewById(R.id.post_menu);
                PostsAdapter adapter = new PostsAdapter(posts);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return g;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("posts",getArguments().getParcelableArrayList("msg"));
    }

    public static Posts newInstance(ArrayList<Post_information> posts) {
        Posts f = new Posts();
        Bundle b = new Bundle();
        b.putParcelableArrayList("msg", posts);
        f.setArguments(b);
        return f;
    }


}
