package com.ahmad.tabdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad on 2/17/2016.
 */
public class ComInfo {
    private String Name;
    private int num;
    public ComInfo(String Name,int num){
        this.Name=Name;
        this.num=num;
    }
    public String getName(){
        return Name;
    }
    public int getNum(){
        return num;
    }
    public static List<ComInfo> createCom() {
        List<ComInfo> comunity = new ArrayList<ComInfo>();
        String Names[]={"Happiness for all","Hope","نحن نحب الحشيش"};
        int nums[]={12,6,2};
        for (int i=0;i<=nums.length-1;i++){
            comunity.add(new ComInfo(Names[i],nums[i]));
        }
        return comunity;
    }
}
