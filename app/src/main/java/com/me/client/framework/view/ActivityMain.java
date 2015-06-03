package com.me.client.framework.view;


import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ironman.client.view.BaseActivity;
import com.ironman.client.view.bean.Fragments;
import com.me.client.R;
import com.me.client.framework.view.adapter.LeftDrawerAdapter;

import java.util.ArrayList;
import java.util.List;


public class ActivityMain extends BaseActivity {

    public static final String TAG = ActivityMain.class.getName();
    private ArrayList<Fragments> fragmentses;
    private long previousTime = 0;


    @Override
    protected void initDatas() {
        fragmentses = new ArrayList<>();
        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab1"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab2"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab3"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab4"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));
//        fragmentses.add(new Fragments(SearchFragment.newInstance(), "tab5"));

    }

    @Override
    protected List<Fragments> getFragments() {
        return fragmentses;
    }

    @Override
    protected View getLeftDrawer() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_left_drawer, null);
        ListView listLeftDrawer = (ListView) view.findViewById(R.id.left_drawer_list);
        ArrayList<String> data = new ArrayList<>(9);
        data.add("数目检索");
        data.add("热门推荐");
        data.add("分类浏览");
        data.add("新书通报");
        data.add("期刊导航");
        data.add("读者荐购");
        data.add("学科参考");
        data.add("信息发布");
        data.add("我的图书馆");
        LeftDrawerAdapter adapter = new LeftDrawerAdapter(data, this);
        listLeftDrawer.setAdapter(adapter);
        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && KeyEvent.KEYCODE_BACK == keyCode) {
            long currentTime = System.currentTimeMillis();
            if((currentTime-previousTime)>=1000) {
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                previousTime = currentTime;
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected View getRightDrawer() {
        return null;
    }
}
