package com.me.client.framework.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.me.client.R;
import com.me.client.framework.App;
import com.me.client.framework.bean.Book;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Administrator on 2015/5/31.
 */
public class SearchAdapter extends BaseAdapter {

    private List<Book> data;
    private Context context;
    private LayoutInflater inflater;

    public SearchAdapter(List<Book> data, Context context) {
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
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_search_item, parent, false);
            holder.imageView = (NetworkImageView) convertView.findViewById(R.id.list_item_left_image);
            holder.title = (TextView) convertView.findViewById(R.id.list_item_left_title);
            holder.bookId = (TextView) convertView.findViewById(R.id.search_book_bookid);
            holder.bookNum = (TextView) convertView.findViewById(R.id.search_book_canborrow);
            holder.place = (TextView) convertView.findViewById(R.id.search_book_place);
            convertView.setTag(holder);
        }else{
            holder  = (ViewHolder) convertView.getTag();
        }

        Book book = data.get(position);
        if(book.imgSrc != null && !"".equals(book.imgSrc)&&book.imgSrc.contains("http")){
            holder.imageView.setImageUrl(book.imgSrc, App.getInstance().getImageLoader());
        }else{
            holder.imageView.setImageUrl("", App.getInstance().getImageLoader());
        }
        holder.imageView.setDefaultImageResId(R.drawable.nobook);
        holder.title.setText(book.name);
        holder.bookId.setText(book.bookId);
        holder.bookNum.setText(book.canBorrowNum);
        holder.place.setText(book.place);
        return convertView;
    }

    public static class ViewHolder{
        public NetworkImageView imageView;
        public TextView title;
        public TextView bookId;
        public TextView bookNum;
        public TextView place;
    }
}
