package com.example.koichung.ViewController.Base;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koichung.R;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class FragmentWithTab extends BaseFragment {
    View mRootView;
    TabLayout tabLayout;
    ViewPager viewPager;
    protected ViewPageAdapter pageAdapter;
    FloatingActionButton fab;
    public FragmentWithTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView=inflater.inflate(R.layout.fragment_fragment_with_tab, container, false);
        init();
        return mRootView;
    }

    private void init() {
        tabLayout=mRootView.findViewById(R.id.tab);
        viewPager=mRootView.findViewById(R.id.vp);
        fab=mRootView.findViewById(R.id.fab);
        pageAdapter=new ViewPageAdapter(getChildFragmentManager());
        configAdapter();
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    protected abstract void configAdapter();



}
