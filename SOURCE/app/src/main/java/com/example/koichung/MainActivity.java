package com.example.koichung;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koichung.Util.AppConfig;
import com.example.koichung.ViewController.Agency.AgencyFragment;
import com.example.koichung.ViewController.Base.BaseFragment;
import com.example.koichung.ViewController.Batch.BatchFragment;
import com.example.koichung.ViewController.Contract.ContractFragment;

import static com.example.koichung.ViewController.Contract.ContractFragment.day;
import static com.example.koichung.ViewController.Contract.ContractFragment.month;
import static com.example.koichung.ViewController.Contract.ContractFragment.myDateListener;
import static com.example.koichung.ViewController.Contract.ContractFragment.year;


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
            BaseFragment fragment =new BatchFragment();
            switch (item.getItemId()) {
                case R.id.nav_summary:
                    return true;
                case R.id.nav_contract:

                    fragment = new ContractFragment();
                    imgLogout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.nav_batch:

                    fragment = new BatchFragment();
                    imgLogout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.nav_order:

                    imgLogout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.nav_agent:
                    txtTitle.setText("Danh sách đại lý");
                    fragment=new AgencyFragment();
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
        txtTitle=findViewById(R.id.txt_tilte);
        imgLogout = toolbar.findViewById(R.id.img_logout);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setupView();
    }

    private void setupView() {

    }
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

}
