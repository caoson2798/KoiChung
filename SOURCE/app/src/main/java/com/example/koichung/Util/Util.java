package com.example.koichung.Util;

import com.google.gson.JsonObject;

import java.text.DecimalFormat;

public class Util {
    public static JsonObject jsonObject;

    public static void baseJson() {
        if (jsonObject != null) {
            jsonObject = null;
        }
        jsonObject = new JsonObject();
        jsonObject.addProperty("userAPI", "madara");
        jsonObject.addProperty("passAPI", "madara");
    }

    public static String adDigitalOnDate(int day, int month, int year) {
        String dayS = "";
        String monthS = "";
        if (1 <= day && day <= 9) {
            dayS = "0" + day;
        } else {
            dayS = day + "";
        }
        if (1 <= month && month <= 9) {
            monthS = "0" + month;

        } else {
            monthS = month + "";
        }
        String date = new StringBuilder().append(dayS).append("/")
                .append(monthS).append("/").append(year).toString();
        return date;
    }

    public static String formatMoney(Integer money) {
        DecimalFormat format = new DecimalFormat("###,###,###");
        return format.format(money);
    }

    // chuyển định dạng chuỗi về sô: 100.000 -> 100000
    public static String convertFormatToNumber(String formated) {

        String[] fragmentNumber = formated.split("\\.");
        String number = null;
        StringBuilder conn=new StringBuilder();

        for (int i = 0; i < fragmentNumber.length; i++) {
            number = conn.append(fragmentNumber[i]).toString();
        }
        return number;

    }
}
