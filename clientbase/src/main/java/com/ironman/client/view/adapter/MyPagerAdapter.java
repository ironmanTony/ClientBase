package com.ironman.client.view.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ironman.client.view.bean.Fragments;

import java.util.List;

/**
 * Created by ironmanli on 15-4-15.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {
    private List<Fragments> fragments;

    public MyPagerAdapter(FragmentManager fm, List<Fragments> fragmentsList) {
        super(fm);
        this.fragments = fragmentsList;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).title;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position).fragment;
    }
}
