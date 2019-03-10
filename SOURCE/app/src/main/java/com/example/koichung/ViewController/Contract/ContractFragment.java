package com.example.koichung.ViewController.Contract;

import android.app.DatePickerDialog;
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
import android.widget.Spinner;
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

import static com.example.koichung.ViewController.Base.SelectActivity.KEY_TPYE;

public class ContractFragment extends FragmentWithListView implements DatePickerDialog.OnDateSetListener {
    public static final int STATUS_ALL = 10;
    public static final int STATUS_WAITING_APPROVE = 0;
    public static final int STATUS_OPEN = 1;
    public static final int STATUS_COMPLETE = 2;
    public static final int STATUS_OVER_DUE = -2;
    public String batchID = "0";
    String fromDateTime="08/29/2017";
    String agencyID="0";
    int status = 10;
    ContractAdapter adapter;
    View headerView;
    static TextView txtChosseDay;
    Spinner spAgency, spBatch;
    ArrayList<Contract> arrData = new ArrayList<>();
    public static DatePicker datePicker;
    public static Calendar calendar;
    public static int year, month, day;
    RelativeLayout rlAgency, rlBatch;
    TextView txtAgency, txtBatch;

    public static DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);

                }
            };

    @Override
    protected void configAdapter() {
        headerView = LayoutInflater.from(getActivity()).inflate(R.layout.header_view_lv, null);
        adapter = new ContractAdapter(arrData, getActivity());
        lvFrag.addHeaderView(headerView);
        lvFrag.setAdapter(adapter);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseJsonContract(STATUS_ALL,batchID,agencyID,fromDateTime);
    }

    @Override
    protected void init() {
        super.init();
        rlAgency = headerView.findViewById(R.id.rl_agentcy);
        rlBatch = headerView.findViewById(R.id.rl_batch);
        txtChosseDay = headerView.findViewById(R.id.txt_choose_day);
        txtAgency = headerView.findViewById(R.id.txt_agency);
        txtBatch = headerView.findViewById(R.id.txt_batch);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
        setUpHeaderView();
    }

    private static void showDate(int year, int month, int day) {
        String date = new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year).toString();
        txtChosseDay.setText(date);


    }

    private void setUpHeaderView() {
        //sự chuyển màn hình cọn đại lý
        rlAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                intent.putExtra(KEY_TPYE, 0);
                startActivityForResult(intent, 113);
            }
        });
        //sự chuyển màn hình cọn đại lý
        rlBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivity(intent);
            }
        });
        txtChosseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().showDialog(999);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        String name;
        if (requestCode == 113 && data != null) {
            agencyID = String.valueOf(data.getIntExtra("agencyID", -1));
            baseJsonContract(status,batchID,agencyID,fromDateTime);
            name = data.getStringExtra("nameAgency");
            txtAgency.setText(name);
        } else {
        }
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
                status=STATUS_ALL;
                break;
            case R.id.mnu_wating_approve:
                status=STATUS_WAITING_APPROVE;
                break;
            case R.id.mnu_open:
                status=STATUS_OPEN;
                break;
            case R.id.mnu_complete:
                status=STATUS_COMPLETE;
                break;
            case R.id.mnu_over_due:
                status=STATUS_OVER_DUE;
                break;
        }
        item.setChecked(true);
        baseJsonContract(status,batchID,agencyID,fromDateTime);
        getData();
        return super.onOptionsItemSelected(item);
    }

    void baseJsonContract(int status,String _batchID,String _agencyID,String fromDateTime) {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        Util.jsonObject.addProperty("status", status);
        Util.jsonObject.addProperty("batchID", _batchID);
        Util.jsonObject.addProperty("agencyID", _agencyID);
        Util.jsonObject.addProperty("fromDateTime", fromDateTime);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

}
