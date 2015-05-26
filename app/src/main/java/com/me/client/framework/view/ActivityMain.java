package com.me.client.framework.view;


import com.ironman.client.view.BaseActivity;
import com.ironman.client.view.bean.Fragments;

import java.util.ArrayList;
import java.util.List;


public class ActivityMain extends BaseActivity {

    private ArrayList<Fragments> fragmentses;


    @Override
    protected void initDatas() {
        fragmentses = new ArrayList<>();
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab1"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab2"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab3"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab4"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));
        fragmentses.add(new Fragments(MainFragment.newInstance(), "tab5"));

    }

    @Override
    protected List<Fragments> getFragments() {
        return fragmentses;
    }
}
