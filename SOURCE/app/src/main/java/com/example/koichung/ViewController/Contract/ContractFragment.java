package com.example.koichung.ViewController.Contract;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.DialogFragment;
import com.example.koichung.Model.Contract;
import com.example.koichung.Model.ContractRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.example.koichung.ViewController.Contract.Adapter.ContractAdapter;
import com.example.koichung.ViewController.Contract.Adapter.SpinnerAgencyAdapter;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractFragment extends FragmentWithListView implements DatePickerDialog.OnDateSetListener   {
    public static final String STATUS_ALL = "10";
    public static final String STATUS_WAITING_APPROVE = "0";
    public static final String STATUS_OPEN = "1";
    public static final String STATUS_COMPLETE = "2";
    public static final String STATUS_OVER_DUE = "-2";
    View headerView;
    ContractAdapter adapter;
    static TextView txtChosseDay;
    Spinner spAgency, spBatch;
    ArrayList<Contract> arrData = new ArrayList<>();
    public static DatePicker datePicker;
    public static Calendar calendar;
    public static int year, month, day;
    public static DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2+1, arg3);

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
        baseJsonContract();
        Util.jsonObject.addProperty("status", STATUS_ALL);
        Util.jsonObject.addProperty("batchID", "0");
        Util.jsonObject.addProperty("agencyID", "0");
        Util.jsonObject.addProperty("fromDateTime", "08/29/2017");

    }

    @Override
    protected void init() {
        super.init();
        spAgency = headerView.findViewById(R.id.sp_agency);
        spBatch = headerView.findViewById(R.id.sp_batch);
        txtChosseDay = headerView.findViewById(R.id.txt_choose_day);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
        setUpHeaderView();
    }

    private static void showDate(int year, int month, int day) {
        String date=new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year).toString();
        txtChosseDay.setText(date);


    }
    private void setUpHeaderView() {
        ArrayList<String> arrAgency = new ArrayList<>();
        arrAgency.add("Tất cả");
        arrAgency.add("minh");
        arrAgency.add("vinh");
        arrAgency.add("trang");
        arrAgency.add("hien");
        SpinnerAgencyAdapter adapterSP = new SpinnerAgencyAdapter(arrAgency, getActivity());
        spAgency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spAgency.setAdapter(adapterSP);

        ArrayList<String> arrBatch = new ArrayList<>();
        arrBatch.add("Tất cả");
        arrBatch.add("KC093434");
        arrBatch.add("KC093434");
        arrBatch.add("KC093434");
        arrBatch.add("KC093434");
        SpinnerAgencyAdapter adapterBatch = new SpinnerAgencyAdapter(arrBatch, getActivity());
        spBatch.setAdapter(adapterBatch);

        txtChosseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().showDialog(999);
                AppCompatDialogFragment newFragment = new AppCompatDialogFragment();
                newFragment.show(getFragmentManager(),"gh");

            }
        });
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
                break;
            case R.id.mnu_wating_approve:
                break;
            case R.id.mnu_open:
                break;
            case R.id.mnu_complete:
                break;
            case R.id.mnu_over_due:
                break;
        }
        item.setChecked(true);
        return super.onOptionsItemSelected(item);
    }

    void baseJsonContract() {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

}
