package com.me.client.framework.view;


import android.view.LayoutInflater;
import android.view.View;

import com.ironman.client.view.BaseActivity;
import com.ironman.client.view.bean.Fragments;
import com.me.client.R;

import java.util.ArrayList;
import java.util.List;


public class ActivityMain extends BaseActivity {

    public static final String TAG = ActivityMain.class.getName();
    private ArrayList<Fragments> fragmentses;


    @Override
    protected void initDatas() {
        fragmentses = new ArrayList<>();
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab1"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab2"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab3"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab4"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));

    }

    @Override
    protected List<Fragments> getFragments() {
        return fragmentses;
    }

    @Override
    protected View getLeftDrawer() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_left_drawer, null);
//        view.findViewById(R.id.left_drawer_text).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e(TAG, "textview received click event");
//            }
//        });
        return view;
    }

    @Override
    protected View getRightDrawer() {
        return null;
    }
}
