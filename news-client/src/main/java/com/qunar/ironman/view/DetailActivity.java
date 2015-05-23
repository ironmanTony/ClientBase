package com.qunar.ironman.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.qunar.ironman.R;

public class DetailActivity extends Activity {

    public static final String DETAIL_URL = "detailUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String url = intent.getStringExtra(DETAIL_URL);
        initView(url);
    }

    private void initView(String url) {
        WebView webView = (WebView) findViewById(R.id.detail_wv);
        webView.loadUrl(url);
    }


}
