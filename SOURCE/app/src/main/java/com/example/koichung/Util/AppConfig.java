package com.example.koichung.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    public static String SHARED_PREFERENCES_NAME = "KOI_CHUNG";
    public static String NAME_USER_ID ="userID";


    public static SharedPreferences.Editor getEditor(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor;
    }
    public static SharedPreferences getShared(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        return sharedPreferences;
    }
    //
    public static void setUserID(Context context, int userID) {
        getEditor(context).putInt(NAME_USER_ID, userID).apply();
    }
    //
    public static int getUserID(Context context) {
        return getShared(context).getInt(NAME_USER_ID, -1);
    }
}
