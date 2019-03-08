package com.example.koichung.Network;

import com.example.koichung.Model.AgencyRespone;
import com.example.koichung.Model.BatchRespone;
import com.example.koichung.Model.ContractRespone;
import com.example.koichung.Model.LoginRespone;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServer {
    //login
    @POST("api/Service/CheckLogin")
    @Headers("Content-Type:application/json")
    Call<LoginRespone> getUser(@Body JsonObject jsonObject);
    //get list batch
    @POST("api/Service/GetListBatch")
    @Headers("Content-Type:application/json")
    Call<BatchRespone> getBatch(@Body JsonObject jsonObject);

    //get list contracts
    @POST("api/Service/GetListContract")
    @Headers("Content-Type:application/json")
    Call<ContractRespone> getContract(@Body JsonObject jsonObject);

    //get agency
    @POST("api/Service/GetListAgency")
    @Headers("Content-Type:application/json")
    Call<AgencyRespone> getAgency(@Body JsonObject jsonObject);
}
