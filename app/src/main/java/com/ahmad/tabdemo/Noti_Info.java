package com.ahmad.tabdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad on 2/17/2016.
 */
public class Noti_Info {

   private String Name;
    private String Text;
    private boolean Seen;
    public Noti_Info(String name,String text,boolean seen){
        Name=name;
        Text=text;
        this.Seen=seen;
    }
public String getName(){
    return Name;
}
    public String getText(){
        return Text;
    }
    public boolean getSeen(){
        return Seen;
    }
    public static List<Noti_Info> CreateNoti(){
        List<Noti_Info> notifications = new ArrayList<Noti_Info>();
        String Names[]={"Adnan Khalil","Ahmad Khalil","Ghassan Khalil"};
        String Tames[]={"<b>Adnan Khalil</b> followed you 3 minutes ago","<b>Ahmad Khalil</b> invited you to join <b>Happiness</b> comunity","<b>Ghassan Khalil</b> liked your post <b> I'm gonna hang out ...</b>"};
        boolean Seens[]={false,false,true};
        for(int i=0; i<=Names.length-1;i++){
            notifications.add(new Noti_Info(Names[i],Tames[i],Seens[i]));
        }
        return  notifications;
    }
}
