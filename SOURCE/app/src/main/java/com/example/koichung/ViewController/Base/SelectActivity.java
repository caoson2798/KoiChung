package com.example.koichung.ViewController.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.koichung.Model.Agency;
import com.example.koichung.Model.AgencyRespone;
import com.example.koichung.Model.Batch;
import com.example.koichung.Model.BatchRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Agency.Adapter.AgencyAdapter;
import com.example.koichung.ViewController.Contract.Adapter.SelectBatchAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectActivity extends BaseActivity {
    Toolbar toolbar;
    ListView lvSelect;
    ArrayList<Agency> arrAgency = new ArrayList<>();
    ArrayList<Batch> arrBatch = new ArrayList<>();
    SelectBatchAdapter adapterBatch;
    AgencyAdapter adapter;
    public static String KEY_TPYE = "tpye";
    public static final int CREATE_BATCH = 2;
    public static final int CHOSSE_AGENCY_FOR_CONTRACT = 0;
    public static final int CHOSSE_BATCH_FOR_CONTRACT = 1;
    int style;
    int batchID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        style = getIntent().getIntExtra(KEY_TPYE, -1);
        batchID =getIntent().getIntExtra("batchID",-2);
        View.inflate(this, R.layout.activity_select, findViewById(R.id.container));
        init();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (style == CHOSSE_AGENCY_FOR_CONTRACT) {
            getSupportActionBar().setTitle("Chọn đại lý");
            baseJsonSelect();
            Util.jsonObject.addProperty("batchID",batchID);
            getDataAgency(Util.jsonObject);
        }
        if (style == CHOSSE_BATCH_FOR_CONTRACT) {
            baseJsonSelect();
            Util.jsonObject.addProperty("status",0);
            getSupportActionBar().setTitle("Chọn lô");
            getDataBatch(Util.jsonObject);
        }

    }

    private void getDataBatch(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getBatch(jsonObject).enqueue(new Callback<BatchRespone>() {
            @Override
            public void onResponse(Call<BatchRespone> call, Response<BatchRespone> response) {
                if (response.body().getStatus() == 1) {
                    arrBatch.clear();
                    Batch batch=new Batch();
                    batch.setBatchID(0);
                    batch.setCode("Tất cả");
                    arrBatch.add(batch);
                    arrBatch.addAll(response.body().getResult());
                    adapterBatch.notifyDataSetChanged();
                } else {
                    Toast.makeText(SelectActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BatchRespone> call, Throwable t) {
            }
        });

    }

    private void init() {
        adapter = new AgencyAdapter(arrAgency, this);
        adapterBatch=new SelectBatchAdapter(arrBatch,this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvSelect = findViewById(R.id.lv_select);
        if (style==CHOSSE_AGENCY_FOR_CONTRACT){
            lvSelect.setAdapter(adapter);
        }
        if (style==CHOSSE_BATCH_FOR_CONTRACT){
            lvSelect.setAdapter(adapterBatch);
        }

        addEvents();

    }

    private void addEvents() {
        lvSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (style == CHOSSE_AGENCY_FOR_CONTRACT) {
                    int agencyID = arrAgency.get(position).getAgencyID();
                    String namAgency = arrAgency.get(position).getName();
                    Intent intent = new Intent();
                    intent.putExtra("nameAgency", namAgency);
                    intent.putExtra("agencyID", agencyID);
                    setResult(113, intent);
                    finish();
                }
                if (style == CREATE_BATCH) {
                    if (arrAgency.get(position).isCheck()) {
                        arrAgency.get(position).setCheck(false);
                    } else {
                        arrAgency.get(position).setCheck(true);
                    }
                    adapter.notifyDataSetChanged();
                }
                if (style==CHOSSE_BATCH_FOR_CONTRACT){
                    String code=arrBatch.get(position).getCode();
                    Intent intent = new Intent();
                    intent.putExtra("code", code);
                    intent.putExtra("batchID",arrBatch.get(position).getBatchID());
                    setResult(114, intent);
                    finish();
                }

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    void getDataAgency(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getAgency(jsonObject).enqueue(new Callback<AgencyRespone>() {
            @Override
            public void onResponse(Call<AgencyRespone> call, Response<AgencyRespone> response) {
                if (response.body().getStatus() == 1) {
                    arrAgency.clear();
                    if (getIntent().getIntExtra(KEY_TPYE, -1) == CHOSSE_AGENCY_FOR_CONTRACT) {
                        Agency agency = new Agency(null, 0, "Tất cả", "Tất cả", null, "", null);
                        arrAgency.add(agency);
                    }
                    if (getIntent().getIntExtra(KEY_TPYE, -1) == CREATE_BATCH) {
                    }
                    arrAgency.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SelectActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<AgencyRespone> call, Throwable t) {
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getIntExtra(KEY_TPYE, -1) == CREATE_BATCH) {
            getMenuInflater().inflate(R.menu.menu_add_batch, menu);
            menu.getItem(0).setTitle("Xong");
        }
        return super.onCreateOptionsMenu(menu);
    }

    ArrayList<Agency> arrSelected = new ArrayList<>();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        arrSelected.clear();
        for (int i = 0; i < arrAgency.size(); i++) {
            if (arrAgency.get(i).isCheck()) {
                arrSelected.add(arrAgency.get(i));
            }
        }
        switch (item.getItemId()) {
            case R.id.mnu_save:
                Intent intent = new Intent();
                intent.putExtra("listAgency", arrSelected);
                setResult(114, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    void baseJsonSelect(){
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(this));
    }
}
