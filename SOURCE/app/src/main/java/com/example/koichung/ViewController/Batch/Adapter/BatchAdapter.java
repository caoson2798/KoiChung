package com.example.koichung.ViewController.Batch.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.koichung.Model.Batch;
import com.example.koichung.R;

import java.util.ArrayList;

public class BatchAdapter extends BaseAdapter {
    ArrayList<Batch> arrData;
    Context context;

    public BatchAdapter(ArrayList<Batch> arrData, Context context) {
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
        ViewHoldet viewHoldet;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_batch,parent,false);
            viewHoldet=new ViewHoldet(convertView);
            convertView.setTag(viewHoldet);
        }else {
            viewHoldet= (ViewHoldet) convertView.getTag();
        }
        Batch batch=arrData.get(position);
        viewHoldet.setUpView(batch);
        return convertView;
    }
    class ViewHoldet{
        TextView txtBatchID,txtCount,txtLastCount,txtRoot,txtDealer,txtBuyer,txtNote,txtNotePrivate,txtDay;

        public ViewHoldet(View view) {
            txtBatchID=view.findViewById(R.id.txt_batch_id);
            txtCount=view.findViewById(R.id.txt_count);
            txtLastCount=view.findViewById(R.id.txt_last_count);
            txtRoot=view.findViewById(R.id.txt_root);
            txtDealer=view.findViewById(R.id.txt_dealer);
            txtBuyer=view.findViewById(R.id.txt_buyer);
            txtNote=view.findViewById(R.id.txt_note);
            txtNotePrivate=view.findViewById(R.id.txt_note_private);
            txtDay=view.findViewById(R.id.txt_day);
        }
        public void setUpView(Batch batch){
            txtBatchID.setText(batch.getCode());
            txtCount.setText(batch.getCount()+"");
            txtLastCount.setText(batch.getLastCount()+"");
            txtRoot.setText(batch.getRoot());
            txtDealer.setText(batch.getDealer());
            txtBuyer.setText(batch.getBuyer());
            txtNote.setText(batch.getNote());
            txtNotePrivate.setText(batch.getPrivateNote());
            txtDay.setText(batch.getCreateDate());
        }
    }

}
