package com.example.koichung.ViewController.Order;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koichung.Model.Order;
import com.example.koichung.Model.OrderRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.example.koichung.ViewController.Base.SelectActivity;
import com.example.koichung.ViewController.Order.Adapter.OrderAdapter;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderFragment extends FragmentWithListView {
    View headerViewOrder;
    String fromDateTime = "15/29/2017";
    int agencyID = 0;
    int contractID = 0;
    OrderAdapter adapter;
    TextView txtChosseDay,txtAgency,txtContract;
    ArrayList<Order> arrOrder = new ArrayList<>();
    RelativeLayout rlAgency, rlContract;
    Calendar calendar = Calendar.getInstance();
    DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            setDate(dayOfMonth,month+1,year);
        }
    };

    private void setDate(int day, int month, int year) {
        String date = Util.adDigitalOnDate(day,month,year);
        txtChosseDay.setText(date);
        fromDateTime = date;
        baseJsonOrder(agencyID,contractID,fromDateTime);
        getData();
    }

    @Override
    protected void configAdapter() {
        setUpHeader();
        lvFrag.addHeaderView(headerViewOrder);
        adapter = new OrderAdapter(arrOrder, getActivity());
        lvFrag.setAdapter(adapter);
    }

    private void setUpHeader() {
        headerViewOrder = LayoutInflater.from(getActivity()).inflate(R.layout.hearder_view_order, null);
        txtChosseDay = headerViewOrder.findViewById(R.id.txt_choose_day);
        rlAgency = headerViewOrder.findViewById(R.id.rl_agentcy);
        rlContract = headerViewOrder.findViewById(R.id.rl_contract);
        txtAgency=headerViewOrder.findViewById(R.id.txt_agency);
        txtContract=headerViewOrder.findViewById(R.id.txt_contract);
        txtChosseDay.setText("Chọn ngày");
        addEvents();
    }

    private void addEvents() {
        rlAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SelectActivity.class);
                intent.putExtra(AppConfig.KEY_TPYE,AppConfig.CHOSSE_AGENCY);
                intent.putExtra("batchID", AppConfig.STATUS_ALL_BATCH);
                startActivityForResult(intent,116);
            }
        });

        rlContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SelectActivity.class);
                intent.putExtra(AppConfig.KEY_TPYE,AppConfig.CHOSSE_CONTRACT);
                intent.putExtra("agencyID",agencyID );
                startActivityForResult(intent,117);
            }
        });
        txtChosseDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) ;
                int day = calendar.get(Calendar.DAY_OF_MONTH)-1;

                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),onDateSetListener,year,month,day);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String name;
        if (requestCode==116 && data !=null){
            agencyID = data.getIntExtra("agencyID", -1);
            name = data.getStringExtra("nameAgency");
            txtAgency.setText(name);
        }
        if (requestCode==117 && data!=null){
            name=data.getStringExtra("code");
            contractID=data.getIntExtra("contractID",-2);
            txtContract.setText(name);
        }
        baseJsonOrder(agencyID,contractID,fromDateTime);
        getData();
    }

    @Override
    protected void getData() {
        super.getData();
        baseJsonOrder(agencyID, contractID, fromDateTime);
        getDataOrder(Util.jsonObject);
    }

    private void getDataOrder(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getOrder(jsonObject).enqueue(new Callback<OrderRespone>() {
            @Override
            public void onResponse(Call<OrderRespone> call, Response<OrderRespone> response) {
                swipeRefreshLayout.setRefreshing(false);
                if (response.body().getStatus() == 1) {
                    arrOrder.clear();
                    arrOrder.addAll(response.body().getResult());
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<OrderRespone> call, Throwable t) {

            }
        });
    }

    void baseJsonOrder(int agencyID, int contractID, String fromDateTime) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(getActivity()));
        String date;
        date = Util.adDigitalOnDate(day, month, year);
        Util.jsonObject.addProperty("toDate", date);
        Util.jsonObject.addProperty("agencyID", agencyID);
        Util.jsonObject.addProperty("contractID", contractID);
        Util.jsonObject.addProperty("fromDate", fromDateTime);

    }
}
