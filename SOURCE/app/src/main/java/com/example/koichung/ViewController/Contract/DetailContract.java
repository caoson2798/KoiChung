package com.example.koichung.ViewController.Contract;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.example.koichung.R;
import com.example.koichung.ViewController.Base.BaseActivity;

public class DetailContract extends BaseActivity {
    TextView txtContractId,txtAgency,txtCount,txtCountBuyed,
            txtLastCount,txtfunds,txtPercentCommit,txtDayCommit,txtFunds,txtCreateDay,txtStatus;
    Switch swShowWeb;
    Button btnUpdate,btnDel,btnFinish,btnCancel,btnCofirm;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this, R.layout.detail_contract,findViewById(R.id.container));
        setUpToolbar();
        init();
    }

    private void init() {
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
