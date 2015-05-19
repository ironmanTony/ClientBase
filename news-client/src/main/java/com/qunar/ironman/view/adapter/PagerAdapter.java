package com.qunar.ironman.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.qunar.ironman.view.FragmentPager;

/**
 * Created by ironmanli on 15-4-15.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES  ;

    public  PagerAdapter(FragmentManager fm, String[] arras) {
        super(fm);
        TITLES = arras;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentPager.newInstance();
    }
}
