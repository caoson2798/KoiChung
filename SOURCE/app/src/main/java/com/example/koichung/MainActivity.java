package com.example.koichung;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.koichung.ViewController.Batch.BatchFragment;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager=getSupportFragmentManager();
            FragmentTransaction transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.nav_summary:

                    return true;
                case R.id.nav_contract:

                    return true;
                case R.id.nav_batch:
                    if (getFragmentManager().findFragmentByTag(BatchFragment.class.getName()) != null) {
                        transaction.remove(BatchFragment.getInstance());
                    }else {
                        transaction.replace(R.id.parent,BatchFragment.getInstance()).commit();
                    }

                    return true;
                case R.id.nav_order:

                    return true;
                case R.id.nav_agent:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
