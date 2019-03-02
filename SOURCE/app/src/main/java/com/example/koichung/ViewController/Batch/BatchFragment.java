package com.example.koichung.ViewController.Batch;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koichung.R;
import com.example.koichung.ViewController.Base.FragmentWithTab;

/**
 * A simple {@link Fragment} subclass.
 */
public class BatchFragment extends FragmentWithTab {
    public static BatchFragment batchFragment=null;

    @Override
    protected void configAdapter() {
        pageAdapter.addFragment(ListBatchFragment.getInstance(),"Tất cả");
        pageAdapter.addFragment(ListBatchFragment.getInstance(),"Có HĐ");
        pageAdapter.addFragment(ListBatchFragment.getInstance(),"Chưa HĐ");
    }
    public static BatchFragment getInstance(){
        if (batchFragment==null){
            batchFragment=new BatchFragment();
        }
        return batchFragment;
    }
}
