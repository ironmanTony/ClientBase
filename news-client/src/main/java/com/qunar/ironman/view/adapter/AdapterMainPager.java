package com.qunar.ironman.view.adapter;

import android.content.Context;
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

import java.util.List;

/**
 * Created by ironmanli on 15-4-16.
 */
public class AdapterMainPager extends BaseAdapter {

    public static final String TAG = AdapterMainPager.class.getName();

    private List<News> data;
    private LayoutInflater inflater;
    private ImageLoader loader;

    private int TYPE_COUNT = 4;
    private int TYPE_IMAGES = 0;
    private int TYPE_IMAGE_LEFT = 1;
    private int TYPE_IMAGE_LONG = 2;
    private int TYPE_IMAGE_THREE = 3;


    public AdapterMainPager(Context context, List<News> data, ImageLoader loader) {
        this.data = data;
        this.inflater = LayoutInflater.from(context);
        this.loader = loader;
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
            ViewImages views;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_images, parent, false);
                views = new ViewImages();
                views.image = (NetworkImageView) convertView.findViewById(R.id.list_item_images_pager);
                views.title = (TextView) convertView.findViewById(R.id.list_item_images_title);
                convertView.setTag(views);
            } else {
                views = (ViewImages) convertView.getTag();
            }

            views.image.setImageUrl(news.getImgSrc(), loader);
            views.title.setText(news.getTitle());

        } else if (getItemViewType(position) == TYPE_IMAGE_LONG) {
            ViewImageLong imageLong;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_image_long, parent, false);
                imageLong = new ViewImageLong();
                imageLong.textTitle = (TextView) convertView.findViewById(R.id.list_item_long_title);
                imageLong.textContent = (TextView) convertView.findViewById(R.id.list_item_long_content);
                imageLong.image = (NetworkImageView) convertView.findViewById(R.id.list_item_long_image);
                convertView.setTag(imageLong);
            } else {
                imageLong = (ViewImageLong) convertView.getTag();
            }
            imageLong.textTitle.setText(news.getTitle());
            imageLong.textContent.setText(news.getDigest());
            imageLong.image.setImageUrl(news.getImgSrc(), AppController.getInstance().getImageLoader());

        } else if (getItemViewType(position) == TYPE_IMAGE_LEFT) {
            ViewImageLfet imageLeft;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_image_left, parent, false);
                imageLeft = new ViewImageLfet();
                imageLeft.textTitle = (TextView) convertView.findViewById(R.id.list_item_left_title);
                imageLeft.textContent = (TextView) convertView.findViewById(R.id.list_item_left_content);
                imageLeft.textComments = (TextView) convertView.findViewById(R.id.list_item_left_comments);
                imageLeft.image = (NetworkImageView) convertView.findViewById(R.id.list_item_left_image);
                convertView.setTag(imageLeft);
            } else {
                imageLeft = (ViewImageLfet) convertView.getTag();
            }
            imageLeft.textTitle.setText(news.getTitle());
            imageLeft.textContent.setText(news.getDigest());
            imageLeft.textComments.setText("跟帖" + news.getReplyCount());
            imageLeft.image.setImageUrl(news.getImgSrc(), AppController.getInstance().getImageLoader());

            Log.e(TAG, "position :"+position +"\n"+news.getTitle());
        } else if (getItemViewType(position) == TYPE_IMAGE_THREE) {
            ViewImageThree imageThree;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.layout_list_item_image_three, parent, false);
                imageThree = new ViewImageThree();
                imageThree.textTitle = (TextView) convertView.findViewById(R.id.list_item_three_title);
                imageThree.textContent = (TextView) convertView.findViewById(R.id.list_item_three_comments);
                imageThree.textComments = (TextView) convertView.findViewById(R.id.list_item_three_comments);
                imageThree.image1 = (NetworkImageView) convertView.findViewById(R.id.list_item_three_image1);
                imageThree.image2 = (NetworkImageView) convertView.findViewById(R.id.list_item_three_image2);
                imageThree.image3 = (NetworkImageView) convertView.findViewById(R.id.list_item_three_image3);
                convertView.setTag(imageThree);
            } else {
                imageThree = (ViewImageThree) convertView.getTag();
            }
            imageThree.textTitle.setText(news.getTitle());
            imageThree.textContent.setText(news.getDigest());
            imageThree.textComments.setText("跟帖" + news.getReplyCount());
            imageThree.image1.setImageUrl(news.getImgSrc(), AppController.getInstance().getImageLoader());
            List<ImageSrc> imgExtra = news.getImgextra();
            imageThree.image2.setImageUrl(imgExtra.get(0).getImgsrc(), AppController.getInstance().getImageLoader());
            imageThree.image3.setImageUrl(imgExtra.get(1).getImgsrc(), AppController.getInstance().getImageLoader());
        }

        return convertView;
    }


    private static class ViewImages {
        public static NetworkImageView image;
        public static TextView title;
    }

    private static class ViewImageLfet {
        public static TextView textTitle;
        public static TextView textContent;
        public static TextView textComments;
        public static NetworkImageView image;
    }

    private static class ViewImageLong {
        public static TextView textTitle;
        public static TextView textContent;
        //        public static TextView textComments;
        public static NetworkImageView image;
    }

    private static class ViewImageThree {
        public static TextView textTitle;
        public static TextView textContent;
        public static TextView textComments;
        public static NetworkImageView image1;
        public static NetworkImageView image2;
        public static NetworkImageView image3;
    }


}
