package com.ahmad.tabdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

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
import java.util.List;
import java.util.Map;


/**
 * Created by Ahmad on 2/6/2016.
 */
public class Post_information implements Parcelable {
    String mText ;
    String mName;
    String imgUrl;
    String DateTime;
    String UserID;
   public static List<Post_information> list = new ArrayList<>();
    public static Boolean loaded = false;
    public Post_information(String text,String name,String url,String DateTime,String id){
        mText=text;
        mName = name;
        imgUrl = url;
        this.DateTime=DateTime;
        UserID=id;
    }

    public Post_information(Parcel in) {
        mText = in.readString();
        mName = in.readString();
        imgUrl = in.readString();
        DateTime = in.readString();
        UserID = in.readString();
    }

    public static final Creator<Post_information> CREATOR = new Creator<Post_information>() {
        @Override
        public Post_information createFromParcel(Parcel in) {
            return new Post_information(in);
        }

        @Override
        public Post_information[] newArray(int size) {
            return new Post_information[size];
        }
    };

    public String getmText(){
        return mText;
    }
    public String getmName(){
        return mName;
    }
    public String getImgUrl(){return  imgUrl;}
    public String getDateTime(){return  DateTime;}
    public String getUserID(){return UserID;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mText);
        dest.writeString(mName);
        dest.writeString(imgUrl);
        dest.writeString(DateTime);
        dest.writeString(UserID);
    }
}