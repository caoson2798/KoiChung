package com.example.koichung.Network;

import com.example.koichung.Model.AgencyRespone;
import com.example.koichung.Model.BatchRespone;
import com.example.koichung.Model.ContractRespone;
import com.example.koichung.Model.CreateBatchRespone;
import com.example.koichung.Model.CreateContractsRespones;
import com.example.koichung.Model.LoginRespone;
import com.example.koichung.Model.OrderRespone;
import com.example.koichung.Model.ReportContractRespone;
import com.example.koichung.Model.ReportGeneralRespone;
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

    //create batch
    @POST("api/Service/CreateBatch")
    @Headers("Content-Type:application/json")
    Call<CreateBatchRespone> createBatch(@Body JsonObject jsonObject);

    //get order
    @POST("api/Service/GetListOrder")
    @Headers("Content-Type:application/json")
    Call<OrderRespone> getOrder(@Body JsonObject jsonObject);

    //get report general
    @POST("api/Service/ReportGeneral")
    @Headers("Content-Type:application/json")
    Call<ReportGeneralRespone> getGeneral(@Body JsonObject jsonObject);
    //get report contract
    @POST("api/Service/ReportByContract")
    @Headers("Content-Type:application/json")
    Call<ReportContractRespone> getSummaryContract(@Body JsonObject jsonObject);

    //create contracts
    @POST("api/Service/CreateContract")
    @Headers("Content-Type:application/json")
    Call<CreateContractsRespones> createContracts(@Body JsonObject jsonObject);


}
