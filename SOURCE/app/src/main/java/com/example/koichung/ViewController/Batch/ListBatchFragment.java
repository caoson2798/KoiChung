package com.example.koichung.ViewController.Batch;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.koichung.Model.Batch;
import com.example.koichung.Model.BatchRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.example.koichung.ViewController.Batch.Adapter.BatchAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBatchFragment extends FragmentWithListView {
    public static ListBatchFragment listBatchFragment = null;
    BatchAdapter adapter;
    ArrayList<Batch> arrData=new ArrayList<>();
    int status;
    public static ListBatchFragment newInstance(int tpye) {
        listBatchFragment = new ListBatchFragment();
        Bundle bundle=new Bundle();
        bundle.putInt("tpye",tpye);
        listBatchFragment.setArguments(bundle);
        return listBatchFragment;
    }

    @Override
    protected void getData() {
        super.getData();
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        if (tpye==FragmentWithListView.TAB_ALL_BATCH){
            Util.jsonObject.addProperty("status",0);
        }else if (tpye==FragmentWithListView.TAB_HAVE_BILL){
            Util.jsonObject.addProperty("status",1);
        }else {
            Util.jsonObject.addProperty("status",-1);
        }
        getDataBatch(Util.jsonObject);
    }

    private void getDataBatch(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getBatch(jsonObject).enqueue(new Callback<BatchRespone>() {
            @Override
            public void onResponse(Call<BatchRespone> call, Response<BatchRespone> response) {
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
            public void onFailure(Call<BatchRespone> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void setUpAdapter() {
        adapter=new BatchAdapter(arrData,getActivity());
        lvFag.setAdapter(adapter);
    }
}
