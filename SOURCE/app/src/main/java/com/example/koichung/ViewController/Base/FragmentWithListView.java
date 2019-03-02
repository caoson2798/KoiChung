package com.example.koichung.ViewController.Base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.koichung.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentWithListView extends BaseFragment {
    public static int TAB_ALL_BATCH=0;
    public static int TAB_HAVE_BILL=1;
    public static int TAB_NOT_BILL=2;
    public int tpye;
    protected ListView lvFag;
    protected SwipeRefreshLayout swipeRefreshLayout;

    View mRootView;
    public FragmentWithListView() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tpye=getArguments().getInt("tpye");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView=inflater.inflate(R.layout.fragment_fragment_with_list_view, container, false);
        intit();
        return mRootView;
    }

    private void intit() {
        lvFag=mRootView.findViewById(R.id.lv_frag);
        swipeRefreshLayout=mRootView.findViewById(R.id.sw_refresh);
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
        setUpAdapter();
    }

    protected void getData(){
        swipeRefreshLayout.setRefreshing(true);
    }

    public abstract void setUpAdapter();

}
