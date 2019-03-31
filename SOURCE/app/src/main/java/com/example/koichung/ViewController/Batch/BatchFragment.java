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
import com.example.koichung.Util.Constant;
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
    BatchAdapter adapter;
    ArrayList<Batch> arrData = new ArrayList<>();
    int status = Constant.STATUS_BATH.STATUS_ALL_BATCH.values;

    @Override
    protected void configAdapter() {
        adapter = new BatchAdapter(arrData, getActivity());
        lvFrag.setAdapter(adapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_batch, menu);
        addMenuItem(menu);
    }

    private void addMenuItem(Menu menu) {
        if (AppConfig.getRole(getActivity())==Constant.ROLE_USER.ROLE_ADMIN.values){
            MenuItem menuItem=menu.add(Menu.NONE,Constant.MNU_ADD,Menu.NONE,"");
            menuItem.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
            menuItem.setIcon(R.mipmap.ic_add_square3x);
        }else {
            return;
        }
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
                status = Constant.STATUS_BATH.STATUS_ALL_BATCH.values;
                Log.d(TAG, "onOptionsItemSelected: đã click");
                break;
            case R.id.mnu_have_contracst:
                status = Constant.STATUS_BATH.STATUS_HAVAE_CONTRACT.values;
                Log.d("fffd", "onOptionsItemSelected: đã click");
                break;
            case R.id.mnu_not_contract:
                status = Constant.STATUS_BATH.STATUS_NOT_CONTRACT.values;
                break;
            case Constant.MNU_ADD:
                Intent intent = new Intent(getActivity(), AddBatchActivity.class);
                getActivity().startActivity(intent);
                break;

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
        baseJsonBatch(status);
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
