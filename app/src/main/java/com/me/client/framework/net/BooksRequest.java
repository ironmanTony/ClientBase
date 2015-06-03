package com.me.client.framework.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.me.client.framework.App;
import com.me.client.framework.bean.Book;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/5/28.
 */
public class BooksRequest extends JsonRequest<List<Book>> {

    public static final String TAG = BooksRequest.class.getName();

    private List<Book> books;

    public BooksRequest(String url,List<Book> data,  Response.Listener<List<Book>> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, null, listener, errorListener);
        this.books = data;
    }

    @Override
    protected Response<List<Book>> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.d(TAG, "----------the data has been get");
            //TODO parse string to get objects
            List<Book> books = parseStrings(jsonString);
            //request isbn
//            for(int i = 0; i < books.size(); i++){
//                App.getInstance().addToRequestQueue(new IsbnRequest(books.get(i).marcNo, books.get(i), new Response.Listener<Book>() {
//                    @Override
//                    public void onResponse(Book response) {
//
//                    }
//                }));
//            }
            return Response.success(books,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    private List<Book> parseStrings(String jsonString){
        books.clear();
        Document document = Jsoup.parse(jsonString);
        Element element = document.getElementById("search_book_list");
        if(element != null){
            Elements bookNames = element.getElementsByClass("book_list_info");
            if(bookNames!=null){
                for(int i = 0;i < bookNames.size(); i++){
                    Book book = new Book();
                    Elements names = bookNames.get(i).getElementsByTag("a");
                    String name = bookNames.get(i).getElementsByTag("h3").get(0).getElementsByTag("a").text();
                    Log.d(TAG, bookNames.get(i).getElementsByTag("h3").text());
                    book.name = name;
                    String[] id = bookNames.get(i).getElementsByTag("h3").text().split(" ");
                    if(id.length > 1){
                        book.bookId = id[id.length-1];
                    }
                    book.marcNo = names.get(0).attr("href").split("=")[1];
                    String str = bookNames.get(i).getElementsByTag("p").get(0).text();
                    Log.d(TAG, str);
                    String[] msgs = str.split(" ");
                    if(msgs.length >= 4){
                        book.canBorrowNum = msgs[1];
                        book.totalNum = msgs[0];
                        book.editor = msgs[2];
                        book.publishingHouse = msgs[3];
                    }
                    books.add(book);
                }
            }
        }
        return books;
    }



}
