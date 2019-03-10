package com.example.koichung.ViewController.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.koichung.R;

public class BaseActivity extends AppCompatActivity {
    public static Toolbar toolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
}
