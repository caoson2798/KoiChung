package com.example.koichung.ViewController.Contract.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.koichung.Model.Batch;
import com.example.koichung.R;
import com.example.koichung.ViewController.Base.BaseFragment;

import java.util.ArrayList;

public class SelectBatchAdapter extends BaseAdapter {
    ArrayList<Batch> arrData;
    Context context;

    public SelectBatchAdapter(ArrayList<Batch> arrData, Context context) {
        this.arrData = arrData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_select_batch,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Batch batch=arrData.get(position);
        viewHolder.setUpView(batch);
        return convertView;
    }
    class ViewHolder{
        TextView txtAll,txtBatchID,txtRoot;

        public ViewHolder(View view) {
            txtAll=view.findViewById(R.id.txt_all);
            txtBatchID=view.findViewById(R.id.txt_batch_id);
            txtRoot=view.findViewById(R.id.txt_root);
        }
        public void setUpView(Batch batch){
            txtBatchID.setText(batch.getCode());
            txtRoot.setText("Nguồn gốc: "+batch.getRoot());
            if (batch.getBatchID()==0){
                txtAll.setVisibility(View.VISIBLE);
                txtRoot.setVisibility(View.INVISIBLE);
                txtBatchID.setVisibility(View.INVISIBLE);
            }else {
                txtAll.setVisibility(View.INVISIBLE);
                txtRoot.setVisibility(View.VISIBLE);
                txtBatchID.setVisibility(View.VISIBLE);
            }
        }
    }
}
