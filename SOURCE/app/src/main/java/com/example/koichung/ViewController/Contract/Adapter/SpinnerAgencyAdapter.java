package com.example.koichung.ViewController.Contract.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.koichung.Model.Agency;
import com.example.koichung.R;

import java.util.ArrayList;

public class SpinnerAgencyAdapter extends BaseAdapter {
    ArrayList<Agency> arrData;
    Context context;

    public SpinnerAgencyAdapter(ArrayList<Agency> arrData, Context context) {
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
        convertView= LayoutInflater.from(context).inflate(R.layout.item_sp_agency,parent,false);
        Agency agency=arrData.get(position);
        TextView txtAgency=convertView.findViewById(R.id.txt_agency_list);
        txtAgency.setText(agency.getName());
        return convertView;
    }
}
