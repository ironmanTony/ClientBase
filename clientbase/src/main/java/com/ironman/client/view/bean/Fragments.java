package com.ironman.client.view.bean;


import android.support.v4.app.Fragment;

/**
 * Created by Administrator on 2015/5/26.
 */
public class Fragments {
    public Fragment fragment;
    public String title;

    public Fragments(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragments() {
    }
}
