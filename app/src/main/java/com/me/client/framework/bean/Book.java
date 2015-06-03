package com.me.client.framework.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/5/28.
 */
public class Book implements Serializable{
    public String name;  //书本名称
    public String editor;  //主编
    public String publishingHouse; //出版社
    public String bookId; //索书号
    public String barcode; //条码号
    public String isbn = "";
    public String place; //馆藏地
    public String canBorrowNum; //可借副本
    public String totalNum; //馆藏副本
    public String detailSrc;
    public String summary;
    public String marcNo;
    public String imgSrc;
    public String status;
}
