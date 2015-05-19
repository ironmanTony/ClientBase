package com.qunar.ironman.view.adapter;

import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by ironmanli on 15-4-16.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter{
    protected List<T> data;

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
