package com.example.koichung.ViewController.Order;

import android.view.View;
import android.widget.TextView;

import com.example.koichung.ViewController.Base.FragmentWithListView;
import com.example.koichung.ViewController.Contract.Adapter.ContractAdapter;

public class OrderFragment extends FragmentWithListView {
    View headerViewOrder;
    public int batchID = 0;
    String fromDateTime = "08/29/2017";
    String agencyID = "0";
    int status = 10;

    View headerViewContract;
    TextView txtChosseDay;
    @Override
    protected void configAdapter() {

    }
}
