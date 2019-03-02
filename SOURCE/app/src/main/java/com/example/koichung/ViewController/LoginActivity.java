package com.example.koichung.ViewController;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.koichung.MainActivity;
import com.example.koichung.Model.LoginResult;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.BaseActivity;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {

    EditText edtUserName,edtPassWord;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init() {
        edtPassWord=findViewById(R.id.edt_pass);
        edtUserName=findViewById(R.id.edt_user);
        btnLogin=findViewById(R.id.btn_login);
        addEvents();
    }

    private void addEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataUser();
            }
        });
    }

    private void getDataUser() {
        final ProgressDialog dialog=new ProgressDialog(this);
        dialog.setMessage("Đang xử lý");
        dialog.setCancelable(false);
        dialog.show();
        String user=edtUserName.getText().toString();
        String pass=edtPassWord.getText().toString();
        Util.baseJson();
        Util.jsonObject.addProperty("username",user);
        Util.jsonObject.addProperty("password",pass);
        RetrofitClient.getCilent().create(APIServer.class).getUser(Util.jsonObject).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                dialog.dismiss();
                if (response.body().getStatus()==1){
                    AppConfig.setUserID(LoginActivity.this,response.body().getResult().getUserID());
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {

            }
        });
    }
}
