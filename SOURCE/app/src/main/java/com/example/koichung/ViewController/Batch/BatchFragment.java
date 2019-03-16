package com.example.koichung.ViewController.Batch;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.koichung.Model.Batch;
import com.example.koichung.Model.BatchRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.example.koichung.ViewController.Batch.Adapter.BatchAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatchFragment extends FragmentWithListView {
    public static BatchFragment batchFragment = null;
    BatchAdapter adapter;
    ArrayList<Batch> arrData = new ArrayList<>();
    int status;

    @Override
    protected void configAdapter() {
        adapter = new BatchAdapter(arrData, getActivity());
        lvFrag.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_batch, menu);
        baseJsonBatch(AppConfig.STATUS_ALL_BATCH);
    }

    private void baseJsonBatch(int status) {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        Util.jsonObject.addProperty("status", status);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnu_all:
                status = AppConfig.STATUS_ALL_BATCH;
                Log.d(TAG, "onOptionsItemSelected: đã click");

                break;
            case R.id.mnu_have_contracst:
                status = AppConfig.STATUS_HAVAE_CONTRACT;
                Log.d("fffd", "onOptionsItemSelected: đã click");
                break;
            case R.id.mnu_not_contract:
                status = AppConfig.STATUS_NOT_CONTRACT;
                break;
            case R.id.mnu_add:
                Intent intent = new Intent(getActivity(), AddBatchActivity.class);
                getActivity().startActivity(intent);

        }
        baseJsonBatch(status);
        getDataBatch(Util.jsonObject);
        item.setChecked(true);
        return true;
    }


    @Override
    protected void getData() {
        super.getData();
        getDataBatch(Util.jsonObject);
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getDataBatch(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getBatch(jsonObject).enqueue(new Callback<BatchRespone>() {
            @Override
            public void onResponse(Call<BatchRespone> call, Response<BatchRespone> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.body().getStatus() == 1) {
                    arrData.clear();
                    arrData.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BatchRespone> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
}
