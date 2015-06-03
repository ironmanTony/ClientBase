package com.me.client.framework.net;

import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonRequest;
import com.me.client.framework.bean.Book;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2015/5/30.
 */
public class DetailRequest extends JsonRequest<Book> {
    public static final String TAG = IsbnRequest.class.getName();
    private  Book book;

    public DetailRequest(Book book, Response.Listener<Book> listener) {
        super(Method.GET, "http://202.114.181.3:8080/opac/ajax_douban.php?isbn="+book.isbn, null, listener, null);
        this.book = book;
    }

    @Override
    protected Response<Book> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            Log.d(TAG, "----------the data has been get----------image");
            parseDetail(jsonString);
            return Response.success(book,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        }
    }

    private void parseDetail(String jsonString) {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            book.imgSrc = jsonObject.getString("image");
            book.detailSrc = jsonObject.getString("link");
            Log.d("image", book.imgSrc);
            Log.d("link", book.detailSrc);
        } catch (JSONException e) {
            Log.e("DetailRequest", e.toString());
        }
    }

}
