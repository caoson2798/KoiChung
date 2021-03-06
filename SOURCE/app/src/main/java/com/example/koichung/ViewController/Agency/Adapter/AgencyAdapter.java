package com.example.koichung.ViewController.Agency.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.koichung.Model.Agency;
import com.example.koichung.R;

import java.util.ArrayList;

public class AgencyAdapter extends BaseAdapter {
    ArrayList<Agency> arrData;
    Context context;

    public AgencyAdapter(ArrayList<Agency> arrData, Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_agency,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Agency agency=arrData.get(position);
        viewHolder.setUpView(agency);
        return convertView;
    }
    class ViewHolder{
        TextView txtAgency,txtAddress,txtAll;
        ImageView imgCheck;

        public ViewHolder(View view) {
            txtAddress=view.findViewById(R.id.txt_address);
            txtAgency=view.findViewById(R.id.txt_agency);
            txtAll=view.findViewById(R.id.txt_all);
            imgCheck=view.findViewById(R.id.img_check);
        }
        public void setUpView(Agency agency){
            txtAgency.setText(agency.getName()+"("+agency.getUserName()+")");
            txtAddress.setText("Địa chỉ: "+agency.getAddress());
            if (agency.getAgencyID()==0){
                txtAll.setVisibility(View.VISIBLE);
                txtAddress.setVisibility(View.INVISIBLE);
                txtAgency.setVisibility(View.INVISIBLE);
            }else {
                txtAll.setVisibility(View.INVISIBLE);
                txtAddress.setVisibility(View.VISIBLE);
                txtAgency.setVisibility(View.VISIBLE);
            }
            if (agency.isCheck()){
                imgCheck.setVisibility(View.VISIBLE);
            }else {
                imgCheck.setVisibility(View.INVISIBLE);
            }

        }
    }
}
