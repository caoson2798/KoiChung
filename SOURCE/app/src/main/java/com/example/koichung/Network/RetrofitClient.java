package com.example.koichung.Network;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofit=null;
    public static String BaseUrl="http://winds.hopto.org/";
    public static Retrofit getCilent(){
        if (retrofit==null){
            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
