package com.ahmad.tabdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Add_Post extends AppCompatActivity {
    Posts posts;
   RequestQueue req;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__post);
        posts = (Posts)getSupportFragmentManager().findFragmentById(R.id.fragmentposts);
        req = Volley.newRequestQueue(getBaseContext());
        Button post = (Button)findViewById(R.id.Post_button);
        final EditText text = (EditText)findViewById(R.id.post_textPost);
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://educula.com/post_insert.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME,Context.MODE_PRIVATE);
                          Profile.callback=true;
                            finish();
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"You are having trouble with your connection!",Toast.LENGTH_LONG);
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<>();
                        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                        String id = sharedPreferences.getString(Config.ID_SHARED_PREF,"");
                        map.put("uid",id);
                        map.put("text",text.getText().toString().trim());
                        return map;
                    }
                };

                req.add(stringRequest);
            }
        });
    }

}
