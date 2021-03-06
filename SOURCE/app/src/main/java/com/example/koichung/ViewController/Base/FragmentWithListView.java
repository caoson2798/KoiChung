package com.example.koichung.ViewController.Base;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.ListView;

import com.example.koichung.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentWithListView extends BaseFragment {
    View mRootView;
    protected ListView lvFrag;
    public SwipeRefreshLayout swipeRefreshLayout;
    public FragmentWithListView() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView=inflater.inflate(R.layout.fragment_with_tab, container, false);
        init();
        return mRootView;
    }

    protected void init() {
        setHasOptionsMenu(true);
        swipeRefreshLayout=mRootView.findViewById(R.id.sw_refresh);
        lvFrag =mRootView.findViewById(R.id.lv_frag);
        swipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        ViewCompat.setNestedScrollingEnabled(lvFrag, true);
        configAdapter();


    }


    protected void getData(){
        swipeRefreshLayout.setRefreshing(true);

    }

    protected abstract void configAdapter();

}
