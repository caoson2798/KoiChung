package com.example.koichung.ViewController.Base;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.koichung.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentWithListView extends BaseFragment {

    View mRootView;
    public FragmentWithListView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView=inflater.inflate(R.layout.fragment_fragment_with_list_view, container, false);
        return mRootView;
    }

}
