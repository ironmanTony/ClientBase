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
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/5/29.
 */
public class PlaceRequest extends JsonRequest<Book> {
    public static final String TAG = PlaceRequest.class.getName();
    private Book book;

    public PlaceRequest(Book book, Response.Listener<Book> listener) {
        super(Request.Method.GET, "http://202.114.181.3:8080/opac/ajax_item.php?marc_no=" + book.marcNo, null, listener, null);
        this.book = book;
    }

    @Override
    protected Response<Book> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            parsePlace(jsonString);
            return Response.success(book,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    private void parsePlace(String jsonString) {
        Document document = Jsoup.parseBodyFragment(jsonString);
        Elements elementsTr = document.getElementsByTag("tr");
        if(elementsTr.size() > 1){
            Log.d("td", elementsTr.get(1).getElementsByTag("td").text());
            Elements elementsTd = elementsTr.get(1).getElementsByTag("td");
            if (elementsTd.size() == 5) {
                book.barcode = elementsTd.get(1).text();
                book.place = elementsTd.get(3).text();
                Log.d("barcode", book.barcode);
                Log.d("place", book.place);
            }
        }
    }
}
