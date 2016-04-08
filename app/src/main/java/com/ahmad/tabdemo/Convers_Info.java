package com.ahmad.tabdemo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmad on 2/16/2016.
 */
public class Convers_Info {

    private String Name;
    private String Message;

    public Convers_Info(String Name,String Message){

        this.Name=Name;
        this.Message=Message;
    }

    public String getName(){
        return Name;
    }
    public String getMessage(){

        return Message;
    }

    public static List<Convers_Info> createNameList(){
        List<Convers_Info> contacts = new ArrayList<Convers_Info>();
        String Names[] = {"Ahmad Khalil","Adnan Khalil","Ghassan Khalil","Katya Khalil","Rola Khalil","Ahmad Khalil","Adnan Khalil","Ghassan Khalil","Katya Khalil","Rola Khalil"};
        String Messages[] = {"Hey Lets Hang...","Hey!","Were is my ...","I'm at Dubai...","Hi!","Hey Lets Hang...","Hey!","Were is my ...","I'm at Dubai...","Hi!"};
        for (int i = 0; i <= Names.length-1; i++) {
            contacts.add(new Convers_Info(Names[i],Messages[i]));
        }

        return contacts;
    }
}
