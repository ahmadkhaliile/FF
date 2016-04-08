package com.ahmad.tabdemo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad on 2/19/2016.
 */
public class Followersinfo implements Parcelable {

    private String Name;
    public static Boolean loaded = false;
    private String Id;
    public static List<Followersinfo> followers = new ArrayList<>();
    public Followersinfo(String name,String ID) {
        Name = name; Id=ID;
    }

    protected Followersinfo(Parcel in) {
        Name = in.readString();
        Id = in.readString();
    }

    public static final Creator<Followersinfo> CREATOR = new Creator<Followersinfo>() {
        @Override
        public Followersinfo createFromParcel(Parcel in) {
            return new Followersinfo(in);
        }

        @Override
        public Followersinfo[] newArray(int size) {
            return new Followersinfo[size];
        }
    };

    public String getName() {
        return Name;
    }
    public String getId(){return Id;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Id);
    }
}
