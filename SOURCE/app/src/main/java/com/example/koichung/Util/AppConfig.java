package com.example.koichung.Util;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    // trạng thái của hợp đồng
    public static final int STATUS_ALL_CONTRACT = 10;
    public static final int STATUS_WAITING_APPROVE_CONTRACT = 0;
    public static final int STATUS_OPEN_CONTRACT = 1;
    public static final int STATUS_COMPLETE_CONTRACT = 2;
    public static final int STATUS_OVER_DUE_CONTRACT = -2;
    //trạng thái của lô hàng
     public static final int STATUS_ALL_BATCH=0;
     public static final int STATUS_HAVAE_CONTRACT=1;
     public static final int STATUS_NOT_CONTRACT=-1;


    public static String KEY_TPYE = "tpye";
    //kiểu bên màn hình SelectActivity
    public static final int CREATE_BATCH = 2;
    public static final int CHOSSE_AGENCY = 0;
    public static final int CHOSSE_BATCH = 1;
    public static final int CHOSSE_CONTRACT=3;
    //shared
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
