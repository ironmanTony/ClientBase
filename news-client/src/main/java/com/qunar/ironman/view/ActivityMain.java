package com.qunar.ironman.view;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.qunar.ironman.R;
import com.qunar.ironman.bean.RightDrawerListItem;
import com.qunar.ironman.event.EventGoDetail;
import com.qunar.ironman.view.adapter.AdapterRightDrawerList;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;


public class ActivityMain extends FragmentActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);

        initView();
        initActionBar();
        initRightDrawer();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.main_container, FragmentContent.newInstance())
                    .commit();
        }
    }

    private void initView() {

    }

    private void initRightDrawer(){
        ListView listView = (ListView) findViewById(R.id.profile_list);
        List<RightDrawerListItem> data = new ArrayList<>();
        data.add(new RightDrawerListItem(getResources().getDrawable(R.drawable.biz_pc_main_mall_icon), "俱乐部", "能赚能花，土豪当家"));
        data.add(new RightDrawerListItem(getResources().getDrawable(R.drawable.biz_pc_main_activity_icon), "活 动", "免费定制你的睡眠音乐"));
        data.add(new RightDrawerListItem(getResources().getDrawable(R.drawable.biz_pc_main_app_icon), "应 用", "挖掘金技术哪里强"));
        data.add(new RightDrawerListItem(getResources().getDrawable(R.drawable.biz_pc_main_game_icon), "游 戏", "梦幻西游，礼包抢先送"));
        AdapterRightDrawerList adapter = new AdapterRightDrawerList(this, data);
        listView.setAdapter(adapter);
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer);
        DrawerArrowDrawable drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_profile) {
            if (drawerLayout.isDrawerOpen(findViewById(R.id.left_drawer)) || drawerLayout.isDrawerOpen(findViewById(R.id.right_drawer))) {
                drawerLayout.closeDrawer(findViewById(R.id.left_drawer));
                drawerLayout.closeDrawer(findViewById(R.id.right_drawer));
            } else {
                drawerLayout.openDrawer(findViewById(R.id.right_drawer));
            }
        }
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(findViewById(R.id.left_drawer)) || drawerLayout.isDrawerOpen(findViewById(R.id.right_drawer))) {
                drawerLayout.closeDrawer(findViewById(R.id.left_drawer));
                drawerLayout.closeDrawer(findViewById(R.id.right_drawer));
            } else {
                drawerLayout.openDrawer(findViewById(R.id.left_drawer));
            }
        }

        return super.onOptionsItemSelected(item);
    }

    public void onEventMainThread(EventGoDetail eventGoDetail){
        if(eventGoDetail != null&& !"".equals(eventGoDetail)){
            Intent intent = new Intent(ActivityMain.this, DetailActivity.class);
            intent.putExtra(DetailActivity.DETAIL_URL, eventGoDetail.url);
            startActivity(intent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
