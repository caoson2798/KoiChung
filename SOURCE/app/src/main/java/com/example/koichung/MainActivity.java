package com.example.koichung;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koichung.ViewController.Agency.AgencyFragment;
import com.example.koichung.ViewController.Base.BaseActivity;
import com.example.koichung.ViewController.Base.BaseFragment;
import com.example.koichung.ViewController.Batch.BatchFragment;
import com.example.koichung.ViewController.Contract.ContractFragment;
import com.example.koichung.ViewController.Order.OrderFragment;
import com.example.koichung.ViewController.Summary.SummaryFragment;


public class MainActivity extends BaseActivity {

    private static final int MENU_ITEM_ITEM1 = 1;
    private TextView txtTitle;

    ImageView imgLogout;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            BaseFragment fragment = new BaseFragment();
            switch (item.getItemId()) {
                case R.id.nav_summary:
                    getSupportActionBar().setTitle("Thông kê");
                    fragment=new SummaryFragment();
                    break;
                case R.id.nav_contract:
                    getSupportActionBar().setTitle("Hợp đồng");
                    fragment = new ContractFragment();
                    break;
                case R.id.nav_batch:
                    getSupportActionBar().setTitle("Lô hàng");
                    fragment = new BatchFragment();
                    break;
                case R.id.nav_order:
                    getSupportActionBar().setTitle("Đơn hàng");
                    fragment=new OrderFragment();
                    break;
                case MENU_ITEM_ITEM1:
                    getSupportActionBar().setTitle("Danh sách đại lý");
                    fragment = new AgencyFragment();
                    break;
            }
            item.setChecked(true);
            transaction.replace(R.id.parent, fragment).commit();
            return true;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this, R.layout.activity_main, findViewById(R.id.container));
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setSelectedItemId(R.id.nav_summary);
        Menu menu=navigation.getMenu();
        MenuItem menuItem=menu.add(Menu.NONE, MENU_ITEM_ITEM1, Menu.NONE, "Đại lý");
        menuItem.setIcon(R.mipmap.ic_agency2x);

    }

}
