package com.qunar.ironman.bean;

import java.util.List;

/**
 * Created by ironmanli on 15-4-17.
 */
public class News {
    private String url_3w;//原文链接
    private String digest;//主要内容
    private String title;//标题
    private int imgType = 0;//当为长图的时候imageType=1
    private int replyCount;//跟帖数
    private String imgSrc;//图片
    private List<ImageSrc> imgextra;//如果这个有内容就是有三张图片

    public String getUrl_3w() {
        return url_3w;
    }

    public void setUrl_3w(String url_3w) {
        this.url_3w = url_3w;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public List<ImageSrc> getImgextra() {
        return imgextra;
    }

    public void setImgextra(List<ImageSrc> imgextra) {
        this.imgextra = imgextra;
    }
}
