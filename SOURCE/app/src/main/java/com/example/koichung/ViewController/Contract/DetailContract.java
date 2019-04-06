package com.example.koichung.ViewController.Contract;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.koichung.Model.Contract;
import com.example.koichung.R;
import com.example.koichung.Util.Constant;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.BaseActivity;

public class DetailContract extends BaseActivity {
    TextView txtContractId,txtAgency,txtCount,txtCountBuyed,
            txtLastCount,txtPercentCommit,txtDayCommit,txtFunds,txtCreateDay,txtStatus,txtMoneyBuyed,txtDayFunds;
    Switch swShowWeb;
    Button btnUpdate,btnDel,btnFinish,btnCancel,btnCofirm;
    Contract detailContract;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this, R.layout.detail_contract,findViewById(R.id.container));
        setUpToolbar();
        init();
        showDetail();
    }

    private void showDetail() {
        detailContract= (Contract) getIntent().getSerializableExtra("detailContract");
        txtContractId.setText(detailContract.getCode());
        txtAgency.setText(detailContract.getAgencyName());
        txtCount.setText(Util.formatMoney(detailContract.getQty()) +"");
        txtCountBuyed.setText(Util.formatMoney(detailContract.getQtyBuyed())+"");
        txtFunds.setText(Util.formatMoney(detailContract.getFunds())+"đ");
        txtMoneyBuyed.setText(Util.formatMoney(detailContract.getMoneyBuyed())+"đ");
        txtLastCount.setText((Util.formatMoney(detailContract.getQty()-detailContract.getQtyBuyed()))+"");
        if (detailContract.getTypecommit()==Constant.TYPE_COMIT_CONTRACT.TYPE_PROFIT.Value) {
            txtPercentCommit.setText(detailContract.getPecentcommit() + "% theo lợi nhuận");
        }else if (detailContract.getTypecommit()==Constant.TYPE_COMIT_CONTRACT.TYPE_FUNDS.Value){
            txtPercentCommit.setText(detailContract.getPecentcommit() + "% giá vốn");
        }

        txtDayCommit.setText(detailContract.getDateCommit());
        txtDayFunds.setText(detailContract.getDateFunds());
        txtCreateDay.setText(detailContract.getCreateDate());
        if (detailContract.getStatus()== Constant.STATUS_CONTRACT.STATUS_COMPLETE_CONTRACT.values) {
            handleStatusContract(Constant.STATUS_CONTRACT.STATUS_COMPLETE_CONTRACT.values);
            txtStatus.setText("Hoàn thành");
        }else if (detailContract.getStatus()==Constant.STATUS_CONTRACT.STATUS_DESTROY_CONTRACTS.values){
            txtStatus.setText("Hủy");
        }else if (detailContract.getStatus()==Constant.STATUS_CONTRACT.STATUS_OPEN_CONTRACT.values){
            txtStatus.setText("Đang thực hiện");
        }else if (detailContract.getStatus()==Constant.STATUS_CONTRACT.STATUS_OVER_DUE_CONTRACT.values){
            txtStatus.setText("Quá hạn");
        }else if (detailContract.getStatus()==Constant.STATUS_CONTRACT.STATUS_WAITING_APPROVE_CONTRACT.values){
            txtStatus.setText("Chờ duyệt");
        }

        if (detailContract.getIsShowWeb()==0){
            swShowWeb.setChecked(false);
        }else {
            swShowWeb.setChecked(true);
        }
    }

    private void handleStatusContract(int status) {

    }

    private void init() {
        txtContractId=findViewById(R.id.txt_contract_id);
        txtAgency=findViewById(R.id.txt_agency);
        txtCount=findViewById(R.id.txt_count);
        txtCountBuyed=findViewById(R.id.txt_count_buyed);
        txtLastCount=findViewById(R.id.txt_last_count);
        txtPercentCommit=findViewById(R.id.txt_percent_commit);
        txtDayCommit=findViewById(R.id.txt_day_comit);
        txtFunds=findViewById(R.id.txt_funds);
        txtCreateDay=findViewById(R.id.txt_create_day);
        txtStatus=findViewById(R.id.txt_status);
        txtMoneyBuyed=findViewById(R.id.txt_money_buyed);
        txtDayFunds=findViewById(R.id.txt_day_funds);
        swShowWeb=findViewById(R.id.sw_show_web);
        btnCofirm=findViewById(R.id.btn_confirm);
        btnCancel=findViewById(R.id.btn_cancel);
        btnDel=findViewById(R.id.btn_del);
        btnFinish=findViewById(R.id.btn_fisnh_contract);
        btnUpdate=findViewById(R.id.btn_update);


    }

    private void setUpToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.comeback);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
