package com.example.koichung;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.koichung.ViewController.Base.BaseFragment;
import com.example.koichung.ViewController.Batch.BatchFragment;
import com.example.koichung.ViewController.Contract.ContractFragment;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            BaseFragment fragment = null;
            switch (item.getItemId()) {
                case R.id.nav_summary:
                    txtTitle.setText("Thống kê");
                    return true;
                case R.id.nav_contract:
                    txtTitle.setText("Hợp đồng");
                    fragment= new ContractFragment();
                    break;
                case R.id.nav_batch:
                    txtTitle.setText("Lô hàng");
                    fragment =new BatchFragment();
                    break;
                case R.id.nav_order:
                    txtTitle.setText("Đơn hàng");
                    break;
                case R.id.nav_agent:
                    txtTitle.setText("Đại lý");
                   break;
            }
            transaction.replace(R.id.parent, fragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        txtTitle=toolbar.findViewById(R.id.txt_title);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
