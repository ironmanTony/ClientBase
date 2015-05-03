package com.me.client.framework.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.me.client.R;


public class ActivityMain extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        //test if ssh is ok
    }



}
