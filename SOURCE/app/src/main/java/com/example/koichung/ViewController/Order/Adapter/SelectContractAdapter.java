package com.example.koichung.ViewController.Order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.koichung.Model.Contract;
import com.example.koichung.R;

import java.util.ArrayList;

public class SelectContractAdapter extends BaseAdapter {
    ArrayList<Contract> arrData;
    Context context;

    public SelectContractAdapter(ArrayList<Contract> arrData, Context context) {
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
        Viewholder viewholder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_select_contract,parent,false);
            viewholder=new Viewholder(convertView);
            convertView.setTag(viewholder);
        }else {
            viewholder= (Viewholder) convertView.getTag();
        }
        Contract contract=arrData.get(position);
        viewholder.setUpView(contract);
        return convertView;
    }
    class Viewholder{
        TextView txtCode,txtAgency,txt_all;

        public Viewholder(View view) {
            txtCode=view.findViewById(R.id.txt_code);
            txtAgency=view.findViewById(R.id.txt_agency);
            txt_all=view.findViewById(R.id.txt_all);
        }
        public void setUpView(Contract contract){
            txtCode.setText(contract.getCode());
            txtAgency.setText("Người phụ trách: "+contract.getAgencyName());
            if (contract.getContractID()==0){
                txt_all.setVisibility(View.VISIBLE);
                txtAgency.setVisibility(View.INVISIBLE);
                txtCode.setVisibility(View.INVISIBLE);
            }else {
                txt_all.setVisibility(View.INVISIBLE);
                txtAgency.setVisibility(View.VISIBLE);
                txtCode.setVisibility(View.VISIBLE);

            }
        }
    }
}
