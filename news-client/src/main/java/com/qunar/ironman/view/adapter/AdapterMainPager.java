package com.qunar.ironman.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.qunar.ironman.AppController;
import com.qunar.ironman.R;
import com.qunar.ironman.bean.ImageSrc;
import com.qunar.ironman.bean.News;
import com.qunar.ironman.event.EventGoDetail;

import java.util.List;

import de.greenrobot.event.EventBus;


/**
 * Created by ironmanli on 15-4-16.
 */
public class AdapterMainPager extends BaseAdapter {

    public static final String TAG = AdapterMainPager.class.getName();

    private List<News> data;
    private LayoutInflater inflater;
    private ImageLoader loader;
    private Context context;

    private int TYPE_COUNT = 4;
    private int TYPE_IMAGES = 0;
    private int TYPE_IMAGE_LEFT = 1;
    private int TYPE_IMAGE_LONG = 2;
    private int TYPE_IMAGE_THREE = 3;


    public AdapterMainPager(Context context, List<News> data, ImageLoader loader) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.loader = loader;
        this.context = context;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        int type;
        if (position == 0) {
            type = TYPE_IMAGES;
        } else if (data.get(position).getImgextra() != null && data.get(position).getImgextra().size() == 2) {
            type = TYPE_IMAGE_THREE;
        } else if (data.get(position).getImgType() == 1) {
            type = TYPE_IMAGE_LONG;
        } else {
            type = TYPE_IMAGE_LEFT;
        }
        return type;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final News news = data.get(position);
        if (getItemViewType(position) == TYPE_IMAGES) {
            ViewImages holderHeader;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_images, parent, false);
                holderHeader = new ViewImages();
                holderHeader.image = (NetworkImageView) convertView.findViewById(R.id.list_item_images_pager);
                holderHeader.title = (TextView) convertView.findViewById(R.id.list_item_images_title);
                convertView.setTag(holderHeader);
            } else {
                holderHeader = (ViewImages) convertView.getTag();
            }

            holderHeader.image.setImageUrl(news.getImgSrc(), loader);
            holderHeader.title.setText(news.getTitle());

        } else if (getItemViewType(position) == TYPE_IMAGE_LONG) {
            ViewImageLong holderLong;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_image_long, parent, false);
                holderLong = new ViewImageLong();
                holderLong.textTitle = (TextView) convertView.findViewById(R.id.list_item_long_title);
                holderLong.textContent = (TextView) convertView.findViewById(R.id.list_item_long_content);
                holderLong.image = (NetworkImageView) convertView.findViewById(R.id.list_item_long_image);
                convertView.setTag(holderLong);
            } else {
                holderLong = (ViewImageLong) convertView.getTag();
            }
            holderLong.textTitle.setText(news.getTitle());
            holderLong.textContent.setText(news.getDigest());
            holderLong.image.setImageUrl(news.getImgSrc(), AppController.getInstance().getImageLoader());

        } else if (getItemViewType(position) == TYPE_IMAGE_LEFT) {
            ViewImageLeft holderLeft;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_image_left, parent, false);
                holderLeft = new ViewImageLeft();
                holderLeft.textTitle = (TextView) convertView.findViewById(R.id.list_item_left_title);
                holderLeft.textContent = (TextView) convertView.findViewById(R.id.list_item_left_content);
                holderLeft.textComments = (TextView) convertView.findViewById(R.id.list_item_left_comments);
                holderLeft.image = (NetworkImageView) convertView.findViewById(R.id.list_item_left_image);
                convertView.setTag(holderLeft);
            } else {
                holderLeft = (ViewImageLeft) convertView.getTag();
            }
            holderLeft.textTitle.setText(news.getTitle());
            holderLeft.textContent.setText(news.getDigest());
            holderLeft.textComments.setText("跟帖" + news.getReplyCount());
            holderLeft.image.setImageUrl(news.getImgSrc(), AppController.getInstance().getImageLoader());

        } else if (getItemViewType(position) == TYPE_IMAGE_THREE) {
            ViewImageThree holderThree;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_image_three, parent, false);
                holderThree = new ViewImageThree();
                holderThree.textTitle = (TextView) convertView.findViewById(R.id.list_item_three_title);
                holderThree.textContent = (TextView) convertView.findViewById(R.id.list_item_three_comments);
                holderThree.textComments = (TextView) convertView.findViewById(R.id.list_item_three_comments);
                holderThree.image1 = (NetworkImageView) convertView.findViewById(R.id.list_item_three_image1);
                holderThree.image2 = (NetworkImageView) convertView.findViewById(R.id.list_item_three_image2);
                holderThree.image3 = (NetworkImageView) convertView.findViewById(R.id.list_item_three_image3);
                convertView.setTag(holderThree);
            } else {
                holderThree = (ViewImageThree) convertView.getTag();
            }
            holderThree.textTitle.setText(news.getTitle());
            holderThree.textContent.setText(news.getDigest());
            holderThree.textComments.setText("跟帖" + news.getReplyCount());
            holderThree.image1.setImageUrl(news.getImgSrc(), AppController.getInstance().getImageLoader());
            List<ImageSrc> imgExtra = news.getImgextra();
            holderThree.image2.setImageUrl(imgExtra.get(0).getImgsrc(), AppController.getInstance().getImageLoader());
            holderThree.image3.setImageUrl(imgExtra.get(1).getImgsrc(), AppController.getInstance().getImageLoader());
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventGoDetail(news.getUrl_3w()));
            }
        });

        return convertView;
    }


    public static class ViewImages {
        public  NetworkImageView image;
        public  TextView title;
    }

    public static class ViewImageLeft {
        public  TextView textTitle;
        public  TextView textContent;
        public  TextView textComments;
        public  NetworkImageView image;
    }

    public static class ViewImageLong {
        public  TextView textTitle;
        public  TextView textContent;
        public  NetworkImageView image;
    }

    public static class ViewImageThree {
        public  TextView textTitle;
        public  TextView textContent;
        public  TextView textComments;
        public  NetworkImageView image1;
        public  NetworkImageView image2;
        public  NetworkImageView image3;
    }


}
