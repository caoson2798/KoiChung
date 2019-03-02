package com.example.koichung.Network;

import com.example.koichung.Model.LoginResult;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServer {
    //login
    @POST("api/Service/CheckLogin")
    @Headers("Content-Type:application/json")
    Call<LoginResult> getUser(@Body JsonObject jsonObject);

}
