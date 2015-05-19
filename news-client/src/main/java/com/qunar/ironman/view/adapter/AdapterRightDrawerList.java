package com.qunar.ironman.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qunar.ironman.R;
import com.qunar.ironman.bean.RightDrawerListItem;

import java.util.List;

/**
 * Created by ironmanli on 15-4-16.
 */
public class AdapterRightDrawerList extends BaseAdapter {

    private List<RightDrawerListItem> data;
    private Context context;

    public AdapterRightDrawerList(Context context, List<RightDrawerListItem> data) {
        this.data = data;
        this.context = context;
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
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_profile_list_item, parent, false);
            holder = new ViewHolder();
            holder.imageTitile = (ImageView) convertView.findViewById(R.id.profile_list_image);
            holder.textTitle = (TextView) convertView.findViewById(R.id.profile_list_title);
            holder.textContent = (TextView) convertView.findViewById(R.id.profile_list_content);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageTitile.setImageDrawable(data.get(position).getImage());
        holder.textTitle.setText(data.get(position).getTitle());
        holder.textContent.setText(data.get(position).getContent());

        return convertView;
    }

    private static class ViewHolder {
        public ImageView imageTitile;
        public TextView textTitle;
        public TextView textContent;
    }
}
