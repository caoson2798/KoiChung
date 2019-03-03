package com.example.koichung;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koichung.Util.AppConfig;
import com.example.koichung.ViewController.Base.BaseFragment;
import com.example.koichung.ViewController.Batch.BatchFragment;
import com.example.koichung.ViewController.Contract.ContractFragment;

public class MainActivity extends AppCompatActivity {

    private TextView txtTitle;
    Toolbar toolbar;
    ImageView imgLogout;

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
                    imgLogout.setVisibility(View.VISIBLE);
                    return true;
                case R.id.nav_contract:
                    txtTitle.setText("Hợp đồng");
                    fragment = new ContractFragment();
                    imgLogout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.nav_batch:
                    txtTitle.setText("Lô hàng");
                    fragment = new BatchFragment();
                    imgLogout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.nav_order:
                    txtTitle.setText("Đơn hàng");
                    imgLogout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.nav_agent:
                    txtTitle.setText("Đại lý");
                    imgLogout.setVisibility(View.INVISIBLE);
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
        txtTitle = toolbar.findViewById(R.id.txt_title);
        imgLogout = toolbar.findViewById(R.id.img_logout);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupView();
    }

    private void setupView() {
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(false);
                builder.setIcon(R.drawable.ic_warning);
                builder.setTitle("Cảnh báo");
                builder.setMessage("Bạn có chắc muốn đăng xuất không?");
                builder.setPositiveButton("Không! đùa đấy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Có chứ!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppConfig.setUserID(MainActivity.this,-1);
                        Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.show();
            }
        });
    }

}
