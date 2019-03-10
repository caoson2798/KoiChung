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
public class BatchFragment extends FragmentWithListView  {
    public static BatchFragment batchFragment = null;
    BatchAdapter adapter;
    ArrayList<Batch> arrData = new ArrayList<>();
    int status;
    int sizeMenu;

    public static BatchFragment getInstance() {
        if (batchFragment == null) {
            batchFragment = new BatchFragment();
        }
        return batchFragment;
    }

    @Override
    protected void configAdapter() {
        adapter = new BatchAdapter(arrData, getActivity());
        lvFrag.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_batch, menu);
        Util.baseJson();

        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        Util.jsonObject.addProperty("status", 0);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        switch (item.getItemId()) {
            case R.id.mnu_all:
                Util.jsonObject.addProperty("status", 0);
                Log.d(TAG, "onOptionsItemSelected: đã click");
                getDataBatch(Util.jsonObject);
                break;
            case R.id.mnu_have_contracst:
                Util.jsonObject.addProperty("status", 1);
                Log.d("fffd", "onOptionsItemSelected: đã click");
                getDataBatch(Util.jsonObject);
                break;
            case R.id.mnu_not_contract:
                Util.jsonObject.addProperty("status", -1);
                getDataBatch(Util.jsonObject);
                break;
            case R.id.mnu_add:
                Intent intent=new Intent(getActivity(),AddBatchActivity.class);
                getActivity().startActivity(intent);

        }
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
