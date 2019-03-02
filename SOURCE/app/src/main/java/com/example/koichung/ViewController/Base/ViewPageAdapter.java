package com.example.koichung.ViewController.Base;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.koichung.ViewController.Batch.BatchFragment;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentStatePagerAdapter {
    ArrayList<BaseFragment> mListFrament=new ArrayList<>();
    ArrayList<String> mListTitle=new ArrayList<>();
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mListFrament.get(i);
    }
    public void addFragment(BaseFragment fragment, String title) {
        mListFrament.add(fragment);
        mListTitle.add(title);
    }
    @Override
    public int getCount() {
        return mListFrament.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mListTitle.get(position);
    }
}
