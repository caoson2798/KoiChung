package com.example.koichung.ViewController.Batch;

import com.example.koichung.ViewController.Base.FragmentWithListView;

public class ListBatchFragment extends FragmentWithListView {
    public static ListBatchFragment listBatchFragment=null;
    public static ListBatchFragment getInstance(){
        if (listBatchFragment==null){
            listBatchFragment=new ListBatchFragment();
        }
        return listBatchFragment;
    }
}
