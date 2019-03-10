package com.example.koichung.ViewController.Contract.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.koichung.Model.Contract;
import com.example.koichung.R;
import com.example.koichung.ViewController.Contract.ContractFragment;

import java.util.ArrayList;

public class ContractAdapter extends BaseAdapter {
    ArrayList<Contract> arrData;
    Context context;

    public ContractAdapter(ArrayList<Contract> arrData, Context context) {
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_contract,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        Contract contract=arrData.get(position);
        viewHolder.setUpView(contract);
        return convertView;
    }
    class ViewHolder{
        TextView txtAgencyID,txtAgency,txtStatus,txtLastCount,txtPerCentComit,txtFunds,txtDayFunds,txtDayComit,txtDay;

        public ViewHolder(View view) {
            txtAgencyID=view.findViewById(R.id.txt_agency_id);
            txtAgency=view.findViewById(R.id.txt_agency);
            txtStatus=view.findViewById(R.id.txt_status);
            txtLastCount=view.findViewById(R.id.txt_last_count);
            txtPerCentComit=view.findViewById(R.id.txt_pecent_commit);
            txtFunds=view.findViewById(R.id.txt_funds);
            txtDayFunds=view.findViewById(R.id.txt_day_funds);
            txtDayComit=view.findViewById(R.id.txt_day_comit);
            txtDay=view.findViewById(R.id.txt_day);
        }
        public void setUpView(Contract contract){
            txtAgencyID.setText(contract.getCode());
            txtAgency.setText(contract.getAgencyName());
            txtLastCount.setText(contract.getQty()+"");
            txtPerCentComit.setText(contract.getPecentcommit()+"% theo vốn lợi nhuận");
            txtFunds.setText(contract.getFunds()+"");
            txtDayFunds.setText(contract.getDateFunds());
            txtDayComit.setText(contract.getDateCommit());
            txtDay.setText(contract.getCreateDate());
            switch (contract.getStatus()){
                case ContractFragment.STATUS_WAITING_APPROVE:
                    txtStatus.setText("Chờ duyệt");
                    break;
                case ContractFragment.STATUS_OPEN:
                    txtStatus.setText("Thực hiện");
                    break;
                case ContractFragment.STATUS_COMPLETE:
                    txtStatus.setText("Hoàn thành");
                    break;
                case ContractFragment.STATUS_OVER_DUE:
                    txtStatus.setText("Quá hạn");
                    break;
            }
        }


    }
}
