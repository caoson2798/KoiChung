package com.example.koichung.ViewController.Order.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.koichung.Model.Order;
import com.example.koichung.R;
import com.example.koichung.Util.Util;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    ArrayList<Order> arrData;
    Context context;

    public OrderAdapter(ArrayList<Order> arrData, Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_order,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Order order=arrData.get(position);
        viewHolder.setUpView(order);
        return convertView;
    }
    class ViewHolder{
        TextView txtOrderID,txtClient,txtNumberPhone,txtCount,txtTotalPrice,txtDay;
        public ViewHolder(View view) {
            txtOrderID=view.findViewById(R.id.txt_order_id);
            txtClient=view.findViewById(R.id.txt_client);
            txtNumberPhone=view.findViewById(R.id.txt_number_phone);
            txtCount=view.findViewById(R.id.txt_count);
            txtTotalPrice=view.findViewById(R.id.txt_total_price);
            txtDay=view.findViewById(R.id.txt_day);
        }
        public void setUpView(Order order){
            txtOrderID.setText(order.getCode());
            txtClient.setText(order.getCustomerName());
            txtNumberPhone.setText(order.getPhone());
            txtCount.setText(Util.formatMoney(order.getQty()) +" con");
            txtTotalPrice.setText(Util.formatMoney(order.getTotalPrice())+"đ");
            txtDay.setText(order.getCreateDateString());
        }
    }
}
