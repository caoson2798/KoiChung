package com.example.koichung.ViewController.Agency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.koichung.Model.Agency;
import com.example.koichung.Model.AgencyRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Agency.Adapter.AgencyAdapter;
import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgencyFragment extends FragmentWithListView {
    AgencyAdapter adapter;
    ArrayList<Agency> arrData=new ArrayList<>();
    @Override
    protected void configAdapter() {
        adapter=new AgencyAdapter(arrData,getActivity());
        lvFrag.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseJsonAgency();
        Util.jsonObject.addProperty("batchID","0");
    }

    @Override
    protected void getData() {
        super.getData();
        getDataAgency(Util.jsonObject);
    }

    private void getDataAgency(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getAgency(jsonObject).enqueue(new Callback<AgencyRespone>() {
            @Override
            public void onResponse(Call<AgencyRespone> call, Response<AgencyRespone> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.body().getStatus()==1){
                    arrData.clear();
                    arrData.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getActivity(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AgencyRespone> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    void baseJsonAgency(){
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
    }
}
