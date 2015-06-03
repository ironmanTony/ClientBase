package com.me.client.framework.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
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
 * Created by Administrator on 2015/5/29.
 */
public class IsbnRequest  extends JsonRequest<Book> {
    public static final String TAG = IsbnRequest.class.getName();
    private  Book book;

    public IsbnRequest(Book book, Response.Listener<Book> listener) {
        super(Method.GET, "http://202.114.181.3:8080/opac/item.php?marc_no="+book.marcNo, null, listener, null);
        this.book = book;
    }

    @Override
    protected Response<Book> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.d(TAG, "----------the data has been get----------Ibsn");
            String[] strs = getIsbn(jsonString).split("=");
            if(strs!=null && strs.length==2){
                book.isbn = strs[1];
            }
            return Response.success(book,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    private String getIsbn(String jsonString) {
        jsonString = jsonString.replaceAll("\\r\\n","");
        Log.d("jsonString", jsonString);
        Pattern pattern = Pattern.compile("isbn=\\d+");
        Matcher matcher = pattern.matcher(jsonString);
        if(matcher.find()){
            Log.d("isbn", matcher.group());
            return matcher.group();
        }
        return "";
    }
}
