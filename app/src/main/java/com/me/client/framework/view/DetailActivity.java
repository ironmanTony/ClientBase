package com.me.client.framework.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.me.client.R;
import com.me.client.framework.App;
import com.me.client.framework.bean.Book;
import com.me.client.framework.net.BooksRequest;
import com.me.client.framework.net.DetailRequest;
import com.me.client.framework.net.IsbnRequest;
import com.me.client.framework.net.PlaceRequest;
import com.me.client.framework.view.adapter.SearchAdapter;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends Activity {

    private String requestTag = SearchFragment.class.getName();
    private String requestIsbnTag = SearchFragment.class.getName() + "isbn";
    private String requestPlaceTag = SearchFragment.class.getName() + "place";
    private String requestDetailTag = SearchFragment.class.getName() + "detail";
    private int placeNum = 0;
    private int isbnNum = 0;
    public static final int TOTAL_BOOK = 20;
    private List<Book> data = new ArrayList<>(20);
    private SearchAdapter adapter;
//    private ProgressDialog

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }

    private void initView() {
        final EditText ed = (EditText) findViewById(R.id.search_et);
        findViewById(R.id.search_btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = ed.getText().toString().trim();
                getData(key);
            }
        });
        adapter = new SearchAdapter(data, this);
        ListView listView = (ListView) findViewById(R.id.search_lv_result);
        listView.setAdapter(adapter);
    }


    private void getData(String keyWords) {
        BooksRequest request = new BooksRequest(getUrl(keyWords), data , new Response.Listener<List<Book>>() {
            @Override
            public void onResponse(List<Book> response) {
                adapter.notifyDataSetChanged();
                if(response.size() > 0){
                    getIsbn(response);
                    getPlace(response);
                }else{
                    Toast.makeText(DetailActivity.this, "没有！！！", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        App.getInstance().addToRequestQueue(request, requestTag);
    }

    private void getPlace(List<Book> response) {
        for (int i = 0; i < response.size(); i++) {
            App.getInstance().addToRequestQueue(new PlaceRequest(response.get(i), new Response.Listener<Book>() {
                @Override
                public void onResponse(Book response) {
                    placeNum++;
                    if (placeNum >= TOTAL_BOOK) {
                        //TODO adapter.notifyDataSetChanged
                    }
                }
            }), requestPlaceTag);
        }
    }

    private void getIsbn(List<Book> response) {
        for (int i = 0; i < response.size(); i++) {
            App.getInstance().addToRequestQueue(new IsbnRequest(response.get(i), new Response.Listener<Book>() {
                @Override
                public void onResponse(Book response) {
                    //TODO image request to reqeustQueue
                    App.getInstance().addToRequestQueue(new DetailRequest(response, new Response.Listener<Book>() {
                        @Override
                        public void onResponse(Book response) {
                            //TODO add imagerequest to request
//                            adapter.notifyDataSetChanged();
//                            isbnNum++;
//                            if (isbnNum >= TOTAL_BOOK) {
//                                //TODO adapter.notifyDataSetChanged
//                            }
                        }
                    }), requestDetailTag);
                }
            }), requestIsbnTag);
        }
    }


    private String getUrl(String key) {
        if (key != null && !"".equals(key)) {
            try {
                String str = URLEncoder.encode(key, "utf-8");
                return "http://202.114.181.3:8080/opac/openlink.php?dept=ALL&title=" + str +
                        "&doctype=ALL&lang_code=ALL&match_flag=forward&displaypg=20&showmode=list&orderby=DESC&sort=CATA_DATE&onlylendable=yes&with_ebook=on&with_ebook=on";
            } catch (UnsupportedEncodingException e) {
                Log.e("DetailActivity", e.toString());
            }
        }
        return "";
    }
}
