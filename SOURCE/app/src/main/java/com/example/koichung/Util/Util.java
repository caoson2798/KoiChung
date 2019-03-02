package com.example.koichung.Util;

import com.google.gson.JsonObject;

public class Util {
    public static JsonObject jsonObject;

    public static void baseJson(){
        if (jsonObject!=null){
            jsonObject=null;
        }
        jsonObject=new JsonObject();
        jsonObject.addProperty("userAPI","madara");
        jsonObject.addProperty("passAPI","madara");
    }
}
