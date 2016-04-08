package com.ahmad.tabdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class OtherProfile extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager pager;
    public static String ID;
   String proName;
    ArrayList<Post_information> listsPosts = new ArrayList<>();
    ArrayList<Followersinfo> listsFollowers = new ArrayList<>();
    RequestQueue requestQueue;
    @Override
    protected void onResume() {
        super.onResume();
        //Bundle extras = getIntent().getExtras();
       //ID = (extras.getString("OtherId"));
       // Toast.makeText(this,String.valueOf(listsPosts.size()),Toast.LENGTH_LONG).show();
       //Profile_Top.setId(ID);

       // Posts.create();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_profile);
        requestQueue = Volley.newRequestQueue(this);
        Bundle extras = getIntent().getExtras();
        ID = (extras.getString("OtherId"));
      //  ProName = (TextView)findViewById(R.id.profileName);
       // posts.setID(ID,true);
        getData(ID);
        pager=(ViewPager)findViewById(R.id.pager1);
        pager.setAdapter(new CustomAdapter(getSupportFragmentManager(),getApplicationContext()));
        tabLayout = (TabLayout)findViewById(R.id.menu1);
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
    }

    private class CustomAdapter extends FragmentStatePagerAdapter{


        private String fragments [] = {"Feelings","Communities","Followers"};
        public CustomAdapter(FragmentManager fm,Context applicationContext) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    return Posts.newInstance(listsPosts);
                case 1:
                  //  return Posts.newInstance(listsPosts);
                case 2:
                    return Followers.newInstance(listsFollowers);
                default:
                    return  null;
            }
        }

        @Override
        public int getCount() {
            return fragments.length;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       // Post_information.loaded=false;
    }

    private void getData(final String id) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://educula.com/post_show.php", new Response.Listener<String>() {
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
                        listsPosts.add(new Post_information(Posttext, PostName, pic, date, uid));
                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.post_menu);
                    PostsAdapter adapter = new PostsAdapter(listsPosts);
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

        requestQueue.add(stringRequest);

        //Followers!!!
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
                        listsFollowers.add(new Followersinfo(FolName,Id));

                    }
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerFollowers);
                    FollowersAdapter adapter = new FollowersAdapter(listsFollowers,getApplicationContext());
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
