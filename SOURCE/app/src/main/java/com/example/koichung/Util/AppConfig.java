package com.example.koichung.Util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Arrays;
import java.util.Optional;

public class AppConfig {

    //shared
    public static String SHARED_PREFERENCES_NAME = "KOI_CHUNG";
    public static String NAME_USER_ID = "userID";
    public static String ROLE = "role";


    public static SharedPreferences.Editor getEditor(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        return editor;
    }

    public static SharedPreferences getShared(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, context.MODE_PRIVATE);
        return sharedPreferences;
    }

    // sửa lại userID của người dùng sau mỗi lần đăng nhập
    public static void setUserID(Context context, int userID) {
        getEditor(context).putInt(NAME_USER_ID, userID).apply();
    }

    // lấy userID để gửi request
    public static int getUserID(Context context) {
        return getShared(context).getInt(NAME_USER_ID, -1);
    }
    // sửa lại role của người dùng sau mỗi lần đăng nhập
    public static void setRole(Context context, int role) {
        getEditor(context).putInt(ROLE, role).apply();
    }

    // lấy role để gửi xác vai trò của người dùng trong ứng dụng
    public static int getRole(Context context) {
        return getShared(context).getInt(ROLE, -1);
    }

}
