package com.example.koichung.ViewController.Batch;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koichung.Model.Agency;
import com.example.koichung.Model.CreateBatchRespone;
import com.example.koichung.Network.APIServer;
import com.example.koichung.Network.RetrofitClient;
import com.example.koichung.R;
import com.example.koichung.Util.AppConfig;
import com.example.koichung.Util.Constant;
import com.example.koichung.Util.Util;
import com.example.koichung.ViewController.Base.BaseActivity;
import com.example.koichung.ViewController.Base.SelectActivity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBatchActivity extends BaseActivity {
    EditText edtBatchID,edtCount,edtRoot,edtDealer,edtBuyer,edtNote,edtNotePrivate;
    LinearLayout lnAgency;
    Switch swShowWeb;
    TextView txtAgency;
    ArrayList<Agency> arrSelected;
    ArrayList<Integer> arrAgencyID=new ArrayList<>();
    String batchID;
    int count;
    String root;
    String dealer;
    String buyer,note,notePrivate;
    int showWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View.inflate(this,R.layout.activity_add_batch,findViewById(R.id.container));
        getSupportActionBar().setTitle("Tạo lô mới");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.mipmap.comeback);
        init();
    }

    private void init() {

        edtBatchID=findViewById(R.id.edt_batch_id);
        edtCount=findViewById(R.id.edt_count);
        edtRoot=findViewById(R.id.edt_root);
        edtDealer=findViewById(R.id.edt_dealer);
        edtBuyer=findViewById(R.id.edt_buyer);
        edtNote=findViewById(R.id.edt_note);
        edtNotePrivate=findViewById(R.id.edt_note_private);
        lnAgency=findViewById(R.id.ln_agency);
        swShowWeb=findViewById(R.id.sw_show_web);
        txtAgency=findViewById(R.id.txt_agency);
        addEvents();
    }

    private void addEvents() {
        lnAgency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddBatchActivity.this, SelectActivity.class);
                intent.putExtra(Constant.KEY_SELECT_TYPE,Constant.SELECT_TYPE.CREATE_BATCH.Value);
                startActivityForResult(intent,114);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==114 && data!=null){
            arrSelected= (ArrayList<Agency>) data.getSerializableExtra("listAgency");
            if (arrSelected.size()==0){
                txtAgency.setText("Chọn đại lý");
            }else {
                StringBuffer listAgency=new StringBuffer();
                for (int i = 0; i <arrSelected.size() ; i++) {
                    listAgency.append(arrSelected.get(i).getName()+", ");
                    arrAgencyID.add(arrSelected.get(i).getAgencyID());
                }
                txtAgency.setText(listAgency.toString());
            }
        }else {
            txtAgency.setText("Chọn đại lý");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_batch,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mnu_save:
                processAddBatch();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void processAddBatch() {
        batchID=edtBatchID.getText().toString();
        count=Integer.parseInt(edtCount.getText().toString());
        root=edtRoot.getText().toString();
        dealer=edtDealer.getText().toString();
        buyer=edtBuyer.getText().toString();
        note=edtNote.getText().toString();
        notePrivate=edtNotePrivate.getText().toString();
        if (swShowWeb.isChecked()){
            showWeb =1;
        }else {
            showWeb=0;
        }
        baseJsonAddBatch(batchID,count,root,dealer,buyer,note,notePrivate,arrAgencyID,showWeb);
        getData(Util.jsonObject);
    }

    private void getData(JsonObject jsonObject) {
        ProgressDialog dialog=new ProgressDialog(this);
        dialog.setCancelable(false);
        dialog.setMessage("Đang xử lý");
        dialog.show();
        RetrofitClient.getCilent().create(APIServer.class).createBatch(jsonObject).enqueue(new Callback<CreateBatchRespone>() {
            @Override
            public void onResponse(Call<CreateBatchRespone> call, Response<CreateBatchRespone> response) {
                dialog.dismiss();
                if (response.body().getStatus()==1){
                    finish();
                }else {

                }
                Toast.makeText(AddBatchActivity.this,response.body().getMessage(),Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<CreateBatchRespone> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    void baseJsonAddBatch(String batchID,int count,String root,String dealer,String buyer,String note, String notePrivate,ArrayList<Integer> listAgency,int showWeb){
        Util.baseJson();
        Util.jsonObject.addProperty("userID", AppConfig.getUserID(this));
        Util.jsonObject.addProperty("code",batchID);
        Util.jsonObject.addProperty("count",count);
        JsonArray jsonListAgency=new JsonArray();
        for (int i = 0; i <listAgency.size() ; i++) {
            jsonListAgency.add(listAgency.get(i));
        }
        Util.jsonObject.add("listAgencyID", jsonListAgency);
        Util.jsonObject.addProperty("root",root);
        Util.jsonObject.addProperty("dealer",dealer);
        Util.jsonObject.addProperty("buyer",buyer);
        Util.jsonObject.addProperty("note",note);
        Util.jsonObject.addProperty("privateNote",notePrivate);
    }
}
