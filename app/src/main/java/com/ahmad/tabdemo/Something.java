package com.ahmad.tabdemo;

import android.content.Context;
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

/**
 * Created by Ahmad on 3/22/2016.
 */

public class Something{
    PostListener commander;
   public static ArrayList<Post_information> list = new ArrayList();;
   String id;
   Context c;
    public interface PostListener{

        void onCreatePostList(ArrayList<Post_information> posts);
    }

    public Something(Context c,PostListener l,String id){
       this.c = c;this.id=id;
        try{
        commander = l; getData(); }catch (Exception e){
            //Toast.makeText(c,"Shit!",Toast.LENGTH_LONG).show();}
            e.printStackTrace();
       // getData();
    }

    }
    private void getData(){
        list.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://educula.com/post_show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray array = jsonObject.getJSONArray("posts");
                    for(int i=array.length()-1;i>=0;i--) {
                        JSONObject curr = array.getJSONObject(i);
                        String Posttext = curr.getString("text");
                        String PostName = curr.getString("name");
                        String pic = curr.getString("pic");
                        String uid = curr.getString("uid");
                        String date = curr.getString("post_date");
                        list.add(new Post_information(Posttext, PostName, pic, date, uid));
                    }
                    //Toast.makeText(c,list.size(),Toast.LENGTH_LONG).show();
                    commander.onCreatePostList(list);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c,"ERRRRRORRRRR!!!!!",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();
                map.put("uid",id);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(c);
        requestQueue.add(stringRequest);
    }
}

