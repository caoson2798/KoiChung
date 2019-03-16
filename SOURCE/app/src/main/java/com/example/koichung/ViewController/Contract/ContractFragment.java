package com.example.koichung.ViewController.Contract;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koichung.Model.Contract;
import com.example.koichung.Model.ContractRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.example.koichung.ViewController.Base.SelectActivity;
import com.example.koichung.ViewController.Contract.Adapter.ContractAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.koichung.Util.AppConfig.STATUS_ALL_CONTRACT;


public class ContractFragment extends FragmentWithListView {

    public int batchID = 0;
    String fromDateTime = "08/29/2017";
    int agencyID =0;
    int status = 10;
    ContractAdapter adapter;
    View headerViewContract;
    TextView txtChosseDay;
    ArrayList<Contract> arrData = new ArrayList<>();
    // public  DatePicker datePicker;
    public Calendar calendar;
    public int year, month, day;
    RelativeLayout rlAgency, rlBatch;
    TextView txtAgency, txtBatch;

    public DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    year = arg1;
                    month = arg2;
                    day = arg3;
                    showDate(arg1, arg2 + 1, arg3);

                }
            };

    @Override
    protected void configAdapter() {
        headerViewContract = LayoutInflater.from(getActivity()).inflate(R.layout.header_view_lv_contract, null);
        adapter = new ContractAdapter(arrData, getActivity());
        lvFrag.addHeaderView(headerViewContract);
        lvFrag.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseJsonContract(STATUS_ALL_CONTRACT, batchID, agencyID, fromDateTime);
    }

    @Override
    protected void init() {
        super.init();
        rlAgency = headerViewContract.findViewById(R.id.rl_agentcy);
        rlBatch = headerViewContract.findViewById(R.id.rl_batch);
        txtChosseDay = headerViewContract.findViewById(R.id.txt_choose_day);
        txtAgency = headerViewContract.findViewById(R.id.txt_agency);
        txtBatch = headerViewContract.findViewById(R.id.txt_batch);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR) - 1;
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        setUpHeaderView();
    }

    private void showDate(int year, int month, int day) {

        String date = Util.adDigitalOnDate(day,month,year);
        txtChosseDay.setText(date);
        fromDateTime = date;
        baseJsonContract(status, batchID, agencyID, fromDateTime);
        getData();


    }

    private void setUpHeaderView() {
        //sự chuyển màn hình cọn đại lý
        rlAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                intent.putExtra("batchID", batchID);
                intent.putExtra(AppConfig.KEY_TPYE, AppConfig.CHOSSE_AGENCY);
                startActivityForResult(intent, 113);
            }
        });
        //sự chuyển màn hình cọn đại lý
        rlBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                intent.putExtra("status", status);
                intent.putExtra(AppConfig.KEY_TPYE, AppConfig.CHOSSE_BATCH);
                startActivityForResult(intent, 114);
            }
        });
        txtChosseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), myDateListener, year, month, day);
                datePickerDialog.show();

            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name;
        if (requestCode == 113 && data != null) {
            agencyID = data.getIntExtra("agencyID", -1);
            name = data.getStringExtra("nameAgency");
            txtAgency.setText(name);
        }
        if (requestCode == 114 & data != null) {
            String code = data.getStringExtra("code");
            batchID = data.getIntExtra("batchID", -1);
            txtBatch.setText(code);
        }
        baseJsonContract(status, batchID, agencyID, fromDateTime);
        getData();
    }

    @Override
    protected void getData() {
        super.getData();
        getDataContract(Util.jsonObject);
    }

    private void getDataContract(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getContract(jsonObject).enqueue(new Callback<ContractRespone>() {
            @Override
            public void onResponse(Call<ContractRespone> call, Response<ContractRespone> response) {

                swipeRefreshLayout.setRefreshing(false);
                if (response.body().getStatus() == 1) {
                    arrData.clear();
                    arrData.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ContractRespone> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Log.d("ffrrgg", "onFailure: " + t.toString());
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_contract, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.mnu_all:
                status = AppConfig.STATUS_ALL_CONTRACT;
                break;
            case R.id.mnu_wating_approve:
                status = AppConfig.STATUS_WAITING_APPROVE_CONTRACT;
                break;
            case R.id.mnu_open:
                status = AppConfig.STATUS_OPEN_CONTRACT;
                break;
            case R.id.mnu_complete:
                status = AppConfig.STATUS_COMPLETE_CONTRACT;
                break;
            case R.id.mnu_over_due:
                status = AppConfig.STATUS_OVER_DUE_CONTRACT;
                break;
        }
        item.setChecked(true);
        baseJsonContract(status, batchID, agencyID, fromDateTime);
        getData();
        return super.onOptionsItemSelected(item);
    }

    void baseJsonContract(int status, int _batchID, int _agencyID, String fromDateTime) {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        Util.jsonObject.addProperty("status", status);
        Util.jsonObject.addProperty("batchID", _batchID);
        Util.jsonObject.addProperty("agencyID", _agencyID);
        Util.jsonObject.addProperty("fromDateTime", fromDateTime);
        Log.d("fffg", "baseJsonContract: ");

    }

    public Dialog onCreateDialog(int id) {
        if (id == 999) {
            return new DatePickerDialog(getActivity(),
                    myDateListener, year, month, day);
        }
        return null;
    }


}
