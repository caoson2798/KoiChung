package com.example.koichung.ViewController.Summary;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.koichung.LoginActivity;
import com.example.koichung.Model.ReportContractRespone;
import com.example.koichung.Model.ReportGeneralRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.BaseFragment;
import com.example.koichung.ViewController.Base.SelectActivity;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummaryFragment extends BaseFragment {
    View rootView;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView txtTotalContract, txtTotalBatch, txtTotalAgency, txtContract, txtAgency;
    RelativeLayout rlAllContract, rlAllAgency;
    EditText edtNumberOrder, edtMoneyBuyed, edtTotalFunds, edtProfit;
    int agencyID=3, contractID=0;
    boolean isChosseAgency=false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_summary, container, false);
        intit();

        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        return rootView;
    }

    private void getData() {
        baseJsonSummary(AppConfig.getUserID(getActivity()));
        getReportGeneral(Util.jsonObject);
        //
        baseJsonSummary(agencyID);
        Util.jsonObject.addProperty("contractID", contractID);
        getReportContract(Util.jsonObject);
    }

    private void getReportGeneral(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getGeneral(jsonObject).enqueue(new Callback<ReportGeneralRespone>() {
            @Override
            public void onResponse(Call<ReportGeneralRespone> call, Response<ReportGeneralRespone> response) {
                txtTotalContract.setText(response.body().getResult().getTotalContract() + "");
                txtTotalBatch.setText(response.body().getResult().getTotalBatch() + "");
                txtTotalAgency.setText(response.body().getResult().getTotalAgency() + "");
            }

            @Override
            public void onFailure(Call<ReportGeneralRespone> call, Throwable t) {

            }
        });
    }

    private void getReportContract(JsonObject jsonObject) {
        RetrofitClient.getCilent().create(APIServer.class).getSummaryContract(jsonObject).enqueue(new Callback<ReportContractRespone>() {
            @Override
            public void onResponse(Call<ReportContractRespone> call, Response<ReportContractRespone> response) {
                swipeRefreshLayout.setRefreshing(false);
                edtNumberOrder.setText(response.body().getResult().getNumberOrders() + " đơn");
                edtMoneyBuyed.setText(Util.formatMoney(response.body().getResult().getMoneyBuyed()) + "đ");
                edtTotalFunds.setText(Util.formatMoney(response.body().getResult().getTotalFunds()) + " đ");
                edtProfit.setText(Util.formatMoney(response.body().getResult().getProfit()) + " đ");
            }

            @Override
            public void onFailure(Call<ReportContractRespone> call, Throwable t) {

            }
        });
    }

    private void intit() {
        swipeRefreshLayout = rootView.findViewById(R.id.sw_refresh);
        txtTotalContract = rootView.findViewById(R.id.txt_contract_total);
        txtTotalBatch = rootView.findViewById(R.id.txt_batch_total);
        txtAgency = rootView.findViewById(R.id.txt_agency);
        txtTotalAgency = rootView.findViewById(R.id.txt_agency_total);
        txtContract = rootView.findViewById(R.id.txt_contract);
        rlAllAgency = rootView.findViewById(R.id.rl_all_agency);
        rlAllContract = rootView.findViewById(R.id.rl_all_contract);
        edtNumberOrder = rootView.findViewById(R.id.edt_number_order);
        edtMoneyBuyed = rootView.findViewById(R.id.edt_money_buyed);
        edtTotalFunds = rootView.findViewById(R.id.edt_total_funds);
        edtProfit = rootView.findViewById(R.id.edt_profit);
        addEvents();
    }

    private void addEvents() {
        rlAllAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                intent.putExtra(AppConfig.KEY_TPYE, AppConfig.CHOSSE_AGENCY);
                intent.putExtra("batchID", AppConfig.STATUS_ALL_BATCH);
                startActivityForResult(intent, 114);

            }
        });
        rlAllContract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isChosseAgency){
                    AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
                    builder.setTitle("Bạn chưa chọn đại lý");
                    builder.setCancelable(false);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }else {
                    Intent intent = new Intent(getActivity(), SelectActivity.class);
                    intent.putExtra(AppConfig.KEY_TPYE, AppConfig.CHOSSE_CONTRACT);
                    intent.putExtra("batchID", AppConfig.STATUS_ALL_BATCH);
                    intent.putExtra("agencyID", agencyID);
                    startActivityForResult(intent, 113);
                }

            }
        });

    }
    String name;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 114 && data != null) {
            name = data.getStringExtra("nameAgency");
            agencyID = data.getIntExtra("agencyID", -2);
            isChosseAgency=true;
            checkAllAgency(agencyID);
            baseJsonSummary(agencyID);

        }
        if (requestCode == 113 && data != null) {
            name = data.getStringExtra("code");
            contractID = data.getIntExtra("contractID", -2);
            txtContract.setText(name);
        }
       // baseJsonSummary(agencyID);
        getData();
    }

    private void checkAllAgency(int agencyID) {
        if (agencyID==0){
            txtAgency.setText("Tất cả đại lý");
            txtContract.setText("Tất cả hợp đồng");
            isChosseAgency=false;
            this.agencyID=3;
            contractID=0;
        }else {
            txtAgency.setText(name);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_summary, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.mnu_logout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Cảnh báo");
            builder.setIcon(R.drawable.ic_warning);
            builder.setMessage("Bạn có chắc muốn đăng xuất không");
            builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    AppConfig.setUserID(getActivity(), -1);
                    Intent intent = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                    getActivity().finish();
                }
            });
            builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }
        return true;
    }

    void baseJsonSummary(int userID) {
        Util.baseJson();
        Util.jsonObject.addProperty("userID", userID);
    }
}
