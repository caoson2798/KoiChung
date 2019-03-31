package com.example.koichung.ViewController.Contract;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koichung.Model.CreateContractsRespones;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Constant;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.BaseActivity;
import com.example.koichung.ViewController.Base.NumberTextWatcher;
import com.example.koichung.ViewController.Base.SelectActivity;
import com.example.koichung.ViewController.Base.SuffixEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddContractActivity extends BaseActivity {
    SuffixEditText edtCount;
    EditText edtFund, edtPercent;
    RelativeLayout rlBatch, rlAgency;
    TextView txtBatch, txtAgency, txtDayFunds, txtDayProfit;
    RadioButton rbProfit, rbFunds;
    int lastCount = 0;
    NumberTextWatcher countWatcher, fundsWatcher;
    TextWatcher textWatcherPercent;
    int batchID = 0;
    int agencyID;
    String code=null;
    String nameAgency=null;
    boolean isChosseBatch;
    public static int typeCommit=1;
    Calendar calendar;
    public int year, month, day;
    String dateFunds,dateComit;

    DatePickerDialog.OnDateSetListener onDateSetListenerFunds =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            setDateFunds(dayOfMonth,month+1,year);
        }
    };
    DatePickerDialog.OnDateSetListener onDateSetListenerProfit =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            setDateProfit(dayOfMonth,month+1,year);
        }
    };

    private void setDateProfit(int day, int month, int year) {
        String date = Util.adDigitalOnDate(day,month,year);
        txtDayProfit.setText(date);
        dateComit=date;
    }


    private void setDateFunds(int day, int month, int year) {
        String date = Util.adDigitalOnDate(day,month,year);
        txtDayFunds.setText(date);
        dateFunds=date;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this, R.layout.activity_add_contract, findViewById(R.id.container));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Tạo hợp đồng");
        toolbar.setNavigationIcon(R.mipmap.comeback);
        init();
        addEvents();
    }

    private void addEvents() {
        txtDayProfit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddContractActivity.this, onDateSetListenerProfit,year,month,day);
                datePickerDialog.show();
            }
        });
        txtDayFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(AddContractActivity.this, onDateSetListenerFunds,year,month,day);
                datePickerDialog.show();

            }
        });


        rlBatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddContractActivity.this, SelectActivity.class);
                intent.putExtra(Constant.KEY_SELECT_TYPE, Constant.CHOSSE_BATCH);
                startActivityForResult(intent, 113);
            }
        });
        rlAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (isChosseBatch){
                   Intent intent = new Intent(AddContractActivity.this, SelectActivity.class);
                   intent.putExtra(Constant.KEY_SELECT_TYPE, Constant.CHOSSE_AGENCY);
                   intent.putExtra("batchID", batchID);
                   startActivityForResult(intent, 114);
               }else {
                   Toast toast=new Toast(AddContractActivity.this);
                   toast.setView(LayoutInflater.from(AddContractActivity.this).inflate(R.layout.toast_layot,null));
                   toast.setDuration(Toast.LENGTH_LONG);
                   TextView txtMsg=toast.getView().findViewById(R.id.txt_msg);
                   txtMsg.setText("Bạn chưa chọn lô hàng");
                   toast.show();
               }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 113 && data!=null) {
            isChosseBatch=true;
            code= data.getStringExtra("code");
            batchID = data.getIntExtra("batchID", -2);
            lastCount = data.getIntExtra("count", -1);
            edtCount.setSuffix("/" +  Util.formatMoney(lastCount));
            txtBatch.setText(code);

        }else {
            if (code==null){
                txtBatch.setText("chọn lô hàng");
                isChosseBatch=false;
            }else {
                txtBatch.setText(code);
            }
        }
        //
        if (requestCode==114 && data!=null ){
            agencyID =data.getIntExtra("agencyID",-2);
            nameAgency=data.getStringExtra("nameAgency");
            txtAgency.setText(nameAgency);
        }else {
            if (nameAgency==null){
                txtAgency.setText("chọn đại lý");
            }else {
                txtAgency.setText(nameAgency);
            }
        }

    }

    private void init() {
        edtFund = findViewById(R.id.edt_funds);
        edtPercent = findViewById(R.id.edt_percent);
        rlAgency = findViewById(R.id.rl_agentcy);
        rlBatch = findViewById(R.id.rl_batch);
        txtBatch = findViewById(R.id.txt_batch);
        txtAgency = findViewById(R.id.txt_agency);
        txtDayFunds = findViewById(R.id.txt_choose_day_funds);
        txtDayProfit = findViewById(R.id.txt_choose_day_profit);
        edtCount = findViewById(R.id.edt_count);
        rbFunds = findViewById(R.id.rb_funds);
        rbProfit = findViewById(R.id.rb_profit);

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH)+1;
        day = calendar.get(Calendar.DAY_OF_MONTH);
        setDateFunds(day,month,year);
        setDateProfit(day,month,year);
        setUpView();

        //0 theo giá, 1theo lợi nhuận
    }

    private void setUpView() {
        edtCount.setSuffix("/" + lastCount);
        countWatcher = new NumberTextWatcher(edtCount);
        edtCount.addTextChangedListener(countWatcher);
        fundsWatcher = new NumberTextWatcher(edtFund);
        edtFund.addTextChangedListener(fundsWatcher);
        textWatcherPercent = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int percent;
                if (s.toString().length() > 0) {
                    percent = Integer.parseInt(s.toString());
                    if (percent >= 100) {
                        edtPercent.removeTextChangedListener(textWatcherPercent);
                        edtPercent.setText("100");
                        edtPercent.addTextChangedListener(textWatcherPercent);
                    }
                } else {
                    return;
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtPercent.addTextChangedListener(textWatcherPercent);

        rbProfit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    typeCommit=1;
                    Toast.makeText(AddContractActivity.this,typeCommit+"",Toast.LENGTH_LONG).show();
                }

            }
        });
        rbFunds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    typeCommit=0;
                    Toast.makeText(AddContractActivity.this,typeCommit+"",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_batch, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_save:
                addData();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
    void baseJsonCreateContracts(int status,int batchID,int agencyID,String funds,String qty,
                                 int typeCommit,int percent,String dateFunds,String dateComit){
        Util.baseJson();
        Util.jsonObject.addProperty("userID",AppConfig.getUserID(this));
        Util.jsonObject.addProperty("status",status);
        Util.jsonObject.addProperty("batchID",batchID);
        Util.jsonObject.addProperty("agencyID",agencyID);
        Util.jsonObject.addProperty("funds",funds);
        Util.jsonObject.addProperty("qty",qty);
        Util.jsonObject.addProperty("typeCommit",typeCommit);
        Util.jsonObject.addProperty("persenCommit",percent);
        Util.jsonObject.addProperty("dateFund",dateFunds);
        Util.jsonObject.addProperty("dateCommit",dateComit);
    }

    void addData(){
        String funds= Util.convertFormatToNumber(edtFund.getText().toString());
        String qty=Util.convertFormatToNumber(edtCount.getText().toString());
        int percent= Integer.parseInt(edtPercent.getText().toString());
        baseJsonCreateContracts(1,batchID, agencyID,funds,qty,typeCommit,percent,dateFunds,dateComit);
        RetrofitClient.getCilent().create(APIServer.class).createContracts(Util.jsonObject).enqueue(new Callback<CreateContractsRespones>() {
            @Override
            public void onResponse(Call<CreateContractsRespones> call, Response<CreateContractsRespones> response) {
                if (response.body().getStatus()==1){
                    finish();
                }
                Toast.makeText(AddContractActivity.this,response.body().getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<CreateContractsRespones> call, Throwable t) {
                Log.d("grgg", "onFailure: "+t.toString());
            }
        });
    }
}
