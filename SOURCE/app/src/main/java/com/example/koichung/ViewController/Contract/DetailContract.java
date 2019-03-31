package com.example.koichung.ViewController.Contract;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.koichung.R;
import com.example.koichung.ViewController.Base.BaseActivity;

public class DetailContract extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this, R.layout.detail_contract,findViewById(R.id.container));
    }
}
