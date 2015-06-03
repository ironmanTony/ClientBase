package com.me.client.framework.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.me.client.R;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2015/6/3.
 */
public class LeftDrawerAdapter extends BaseAdapter {

    private List<String> data;
    private Context context;
    private LayoutInflater inflater;

    public LeftDrawerAdapter(List<String> data, Context context) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
    }

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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_left_drawer_item, parent, false);
            holder.textView = (TextView) convertView.findViewById(R.id.left_drawer_item_tv);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(data.get(position));
        return convertView;
    }

    public static class ViewHolder{
        TextView textView;
    }
}
