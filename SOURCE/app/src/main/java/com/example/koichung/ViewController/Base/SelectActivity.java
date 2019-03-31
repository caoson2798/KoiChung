package com.example.koichung.ViewController.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
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
import com.example.koichung.Model.Contract;
import com.example.koichung.Model.ContractRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Constant;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Agency.Adapter.AgencyAdapter;
import com.example.koichung.ViewController.Contract.Adapter.SelectBatchAdapter;
import com.example.koichung.ViewController.Order.Adapter.SelectContractAdapter;
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
    ArrayList<Contract> arrContract = new ArrayList<>();
    SelectBatchAdapter adapterBatch;
    AgencyAdapter adapterAgency;
    SelectContractAdapter contractAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    int style;
    int batchID;
    int agencyID;
    String fromDate = "07/02/2017";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        style = getIntent().getIntExtra(Constant.KEY_SELECT_TYPE, -1);
        batchID = getIntent().getIntExtra("batchID", -2);
        agencyID = getIntent().getIntExtra("agencyID", -2);
        fromDate = getIntent().getStringExtra("fromDate");
        View.inflate(this, R.layout.activity_select, findViewById(R.id.container));
        init();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });

    }

    private void getData() {
        baseJsonSelect();
        if (style == Constant.SELECT_TYPE.CREATE_BATCH.Value) {
            getSupportActionBar().setTitle("Chọn đại lý");
            Util.jsonObject.addProperty("batchID", Constant.STATUS_ALL_BATCH);
            getDataAgency(Util.jsonObject);
        }
        //kiểu chọn đại lý
        if (style == Constant.CHOSSE_AGENCY_ALL || style == Constant.CHOSSE_AGENCY) {
            getSupportActionBar().setTitle("Chọn đại lý");
            Util.jsonObject.addProperty("batchID", batchID);
            getDataAgency(Util.jsonObject);
        }

        //kiểu chọn lô hàng
        if (style == Constant.CHOSSE_BATCH_ALL || style == Constant.CHOSSE_BATCH) {
            Util.jsonObject.addProperty("status", 0);
            getSupportActionBar().setTitle("Chọn lô");
            getDataBatch(Util.jsonObject);
        }
        //chọn hợp đồng
        if (style == Constant.CHOSSE_CONTRACT_ALL) {
            getSupportActionBar().setTitle("Chọn hợp đồng");
            Util.jsonObject.addProperty("status", Constant.STATUS_ALL_CONTRACT);
            Util.jsonObject.addProperty("batchID", Constant.STATUS_ALL_BATCH);
            Util.jsonObject.addProperty("agencyID", agencyID);
            Util.jsonObject.addProperty("fromDateTime", fromDate);
            getDataContract(Util.jsonObject);

        }
    }

    private void getDataContract(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getContract(jsonObject).enqueue(new Callback<ContractRespone>() {
            @Override
            public void onResponse(Call<ContractRespone> call, Response<ContractRespone> response) {

                swipeRefreshLayout.setRefreshing(false);
                arrContract.clear();
                if (response.body().getStatus() == 1 && response.body().getResult().size() > 0) {
                    Contract contract = new Contract("Tất cả", 0);
                    arrContract.add(contract);
                    arrContract.addAll(response.body().getResult());
                    contractAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(SelectActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ContractRespone> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("ffrrgg", "onFailure: " + t.toString());
            }
        });
    }

    private void getDataBatch(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getBatch(jsonObject).enqueue(new Callback<BatchRespone>() {
            @Override
            public void onResponse(Call<BatchRespone> call, Response<BatchRespone> response) {
                swipeRefreshLayout.setRefreshing(false);
                arrBatch.clear();
                if (response.body().getStatus() == 1 && response.body().getResult().size() > 0 && style == Constant.CHOSSE_BATCH_ALL) {
                    Batch batch = new Batch(0, "Tất cả");
                    arrBatch.add(batch);
                }
                arrBatch.addAll(response.body().getResult());
                adapterBatch.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BatchRespone> call, Throwable t) {
            }
        });

    }

    private void init() {
        swipeRefreshLayout = findViewById(R.id.sw_refresh);
        adapterAgency = new AgencyAdapter(arrAgency, this);
        adapterBatch = new SelectBatchAdapter(arrBatch, this);
        contractAdapter = new SelectContractAdapter(arrContract, this);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        lvSelect = findViewById(R.id.lv_select);
        if (style == Constant.CHOSSE_AGENCY_ALL || style == Constant.CHOSSE_AGENCY || style==Constant.CREATE_BATCH) {
            lvSelect.setAdapter(adapterAgency);
        }
        if (style == Constant.CHOSSE_BATCH_ALL || style == Constant.CHOSSE_BATCH) {
            lvSelect.setAdapter(adapterBatch);
        }
        if (style == Constant.CHOSSE_CONTRACT_ALL) {
            lvSelect.setAdapter(contractAdapter);
        }
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {

            }
        });
        addEvents();

    }

    private void addEvents() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        lvSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (style == Constant.CHOSSE_AGENCY_ALL || style==Constant.CHOSSE_AGENCY) {
                    int agencyID = arrAgency.get(position).getAgencyID();
                    String namAgency = arrAgency.get(position).getUserName();
                    Intent intent = new Intent();
                    intent.putExtra("nameAgency", namAgency);
                    intent.putExtra("agencyID", agencyID);
                    setResult(113, intent);
                    finish();
                }
                if (style == Constant.CREATE_BATCH) {
                    if (arrAgency.get(position).isCheck()) {
                        arrAgency.get(position).setCheck(false);
                    } else {
                        arrAgency.get(position).setCheck(true);
                    }
                    adapterAgency.notifyDataSetChanged();
                }
                if (style == Constant.CHOSSE_BATCH_ALL || style==Constant.CHOSSE_BATCH) {
                    String code = arrBatch.get(position).getCode();
                    Intent intent = new Intent();
                    intent.putExtra("code", code);
                    intent.putExtra("batchID", arrBatch.get(position).getBatchID());
                    intent.putExtra("count",arrBatch.get(position).getLastCount());
                    setResult(114, intent);
                    finish();
                }
                if (style == Constant.CHOSSE_CONTRACT_ALL) {
                    String code = arrContract.get(position).getCode();
                    Intent intent = new Intent();
                    intent.putExtra("code", code);
                    intent.putExtra("contractID", arrContract.get(position).getContractID());
                    setResult(117, intent);
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
                swipeRefreshLayout.setRefreshing(false);
                arrAgency.clear();
                if (style == Constant.CHOSSE_AGENCY_ALL && response.body().getResult().size()>0) {
                    Agency agency = new Agency(null, 0, "Tất cả", "Tất cả", null, "", null);
                    arrAgency.add(agency);

                }
                arrAgency.addAll(response.body().getResult());
                adapterAgency.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<AgencyRespone> call, Throwable t) {
                Log.d("ffrfr", "onFailure: "+t.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getIntExtra(Constant.KEY_SELECT_TYPE, -1) == Constant.CREATE_BATCH) {
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

    void baseJsonSelect() {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(this));
    }
}
