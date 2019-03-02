package com.example.koichung;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.koichung.LoginActivity;
import com.example.koichung.MainActivity;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;

public class SplashActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        if (AppConfig.getUserID(this)==-1){
            intent=new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish();
        }else {
            intent=new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
