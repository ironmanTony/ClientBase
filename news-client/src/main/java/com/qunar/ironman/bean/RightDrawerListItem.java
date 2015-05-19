package com.qunar.ironman.bean;

import android.graphics.drawable.Drawable;

/**
 * Created by ironmanli on 15-4-16.
 */
public class RightDrawerListItem {
    private Drawable image;
    private String title;
    private String content;

    public RightDrawerListItem(Drawable image, String title, String content) {
        this.image = image;
        this.title = title;
        this.content = content;
    }

    public RightDrawerListItem() {
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
