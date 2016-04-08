package com.ahmad.tabdemo;

import android.app.ProgressDialog;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ahmad on 3/18/2016.
 */
public class getPosts {

    String ID;
    Context c;
    List<Post_information> list;
    public getPosts(String ID,Context c){
        this.c = c;
        this.ID = ID;
    }

    public List<Post_information> getData(){
        final ProgressDialog loading = ProgressDialog.show(c, "Loading Data", "Please wait...", false, false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://educula.com/post_show.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                //Toast.makeText(v,response,Toast.LENGTH_LONG).show();
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

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(c, "You are having trouble with your connection!", Toast.LENGTH_LONG).show();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();

                map.put("uid",ID);
                return map;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(c);

        //Adding request to the queue
        requestQueue.add(stringRequest);
        return list;
    }

}
