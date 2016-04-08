package com.ahmad.tabdemo;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Profile extends AppCompatActivity {



    TabLayout tabLayout;
    ViewPager pager;
    public static Activity Profile;
    ArrayList<Post_information> listPosts;
    ArrayList<Followersinfo> listFollowers;
    RequestQueue requestQueue;
    String proName;
    public static Boolean callback = false;
    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(this,"Pause",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(callback){

            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
            getData(sharedPreferences.getString(Config.ID_SHARED_PREF,""));
            callback = false;
        }
       //
        //Profile_Top.setId(sharedPreferences.getString(Config.ID_SHARED_PREF, ""));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Profile = this;
        listPosts = new ArrayList<>();
        listFollowers = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        requestQueue = Volley.newRequestQueue(this);
        getData(sharedPreferences.getString(Config.ID_SHARED_PREF, ""));
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new CustomAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout = (TabLayout) findViewById(R.id.menu);
        tabLayout.setupWithViewPager(pager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }
        });
        FloatingActionButton addpost = (FloatingActionButton) findViewById(R.id.addpost);
        addpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, Add_Post.class);
                startActivity(intent);
            }
        });
        logout();
    }
    private class CustomAdapter extends FragmentStatePagerAdapter {

        private String fragments[] = {"Feelings", "Communities", "Followers"};


        public CustomAdapter(FragmentManager supportFragmentManager, Context applicationContext) {
            super(supportFragmentManager);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    try{return Posts.newInstance(listPosts);}catch (Exception e){e.printStackTrace();}

                case 1:
                    try{}catch (Exception e){e.printStackTrace();}
                    //return  Posts.newInstance(listPosts);
                case 2:
                    try{return Followers.newInstance(listFollowers);}catch (Exception e){e.printStackTrace();}

                   //return Posts.newInstance(listPosts);
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragments[position];
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_actions, menu);

        return super.onCreateOptionsMenu(menu);


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search:
                //setContentView(R.layout.activity_profile);
                Intent intent = new Intent(getBaseContext(), Profile.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
                return true;
            case R.id.settings:
                item.setIcon(R.mipmap.settings_filled);
                setContentView(R.layout.activity_main);
                return true;
            case R.id.menu:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void setActionBar() {

        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setTitle("Main");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
    }

    private void logout() {
        //Creating an alert dialog to confirm logout
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to logout?");
        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                        //Getting out sharedpreferences
                        Post_information.list.clear();
                        Followersinfo.followers.clear();
                        Post_information.loaded = false;
                        Followersinfo.loaded = false;


                        SharedPreferences preferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        //Getting editor
                        SharedPreferences.Editor editor = preferences.edit();

                        //Puting the value false for loggedin
                        editor.putBoolean(Config.LOGGEDIN_SHARED_PREF, false);

                        //Putting blank value to email
                        editor.putString(Config.EMAIL_SHARED_PREF, "");
                        editor.putString(Config.ID_SHARED_PREF, "");


                        //Saving the sharedpreferences
                        editor.commit();
                        Toast.makeText(getBaseContext(), preferences.getString(Config.ID_SHARED_PREF, ""), Toast.LENGTH_LONG).show();

                        //Starting login activity
                        finish();
                        Intent intent = new Intent(Profile.this, Login.class);
                        startActivity(intent);

                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        //Showing the alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private void getData(final String id) {


        listPosts.clear();
        listFollowers.clear();
            //PostsStart :)
            StringRequest PostRequest = new StringRequest(Request.Method.POST, "http://educula.com/post_show.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray array = jsonObject.getJSONArray("posts");
                        for (int i = array.length() - 1; i >= 0; i--) {
                            JSONObject curr = array.getJSONObject(i);
                            String Posttext = curr.getString("text");
                            String PostName = curr.getString("name");
                            String pic = curr.getString("pic");
                            String uid = curr.getString("uid");
                            String date = curr.getString("post_date");
                            listPosts.add(new Post_information(Posttext, PostName, pic, date, uid));
                        }
                        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.post_menu);
                        PostsAdapter adapter = new PostsAdapter(listPosts);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        //Toast.makeText(c,list.size(),Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), "ERRRRRORRRRR!!!!!", Toast.LENGTH_LONG).show();
                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("uid", id);
                    return map;
                }
            };

            requestQueue.add(PostRequest);

        //Followers

        StringRequest FstringRequest = new StringRequest(Request.Method.POST, "http://educula.com/followers.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("followers");
                    for(int i=array.length()-1;i>=0;i--){
                        JSONObject curr = array.getJSONObject(i);
                        String FolName = curr.getString("name");
                        String Id = curr.getString("fid");
                        listFollowers.add(new Followersinfo(FolName,Id));

                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerFollowers);
                    FollowersAdapter adapter = new FollowersAdapter(listFollowers,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                }

                catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("ident",id);
                return map;
            }
        };
        requestQueue.add(FstringRequest);

        //Top!!!
        StringRequest Top = new StringRequest(Request.Method.POST, "http://educula.com/Profile.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //loading.dismiss();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("Profile");
                    for(int i=0;i<array.length();i++) {
                        JSONObject curr = array.getJSONObject(i);
                        proName=curr.getString("name");


                    }
                    TextView profileName = (TextView)findViewById(R.id.profileName);
                    profileName.setText(proName);
                    Profile_Top.setName(proName);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "You are having trouble with your connection!", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                map.put("ident",id);
                return map;
            }
        };
        requestQueue.add(Top);
}



}


