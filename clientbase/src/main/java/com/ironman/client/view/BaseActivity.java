package com.ironman.client.view;

import android.app.ActionBar;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import com.ironman.client.R;
import com.ironman.client.view.adapter.MyPagerAdapter;
import com.ironman.client.view.bean.Fragments;
import com.ironman.client.view.widget.ldrawer.ActionBarDrawerToggle;
import com.ironman.client.view.widget.ldrawer.DrawerArrowDrawable;
import com.ironman.client.view.widget.pagerslidingtabstrip.PagerSlidingTabStrip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/5/26.
 */
public abstract class BaseActivity extends FragmentActivity {

    public static final String TAG = BaseActivity.class.getName();
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout drawerLayout;
    private MyPagerAdapter adapter;
    private ArrayList<Fragments> fragments;
    private boolean isLeftDrawerNull = true;
    private boolean isRightDrawerNull = true;
    private PagerSlidingTabStrip tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_base);
        initActionBar();
        if(!initLeftDrawer()){
            //TODO lock left drawer
        }
        if(!initRightDrawer()){
            //TODO lock right drawer
        }
        initDatas();
        initPagerSlidingTabStrip();
    }

    protected abstract void initDatas();

    private boolean initLeftDrawer(){
        View view = getLeftDrawer();
        if(view != null){
            isLeftDrawerNull = false;
            FrameLayout leftContainer = (FrameLayout) findViewById(R.id.left_drawer);
            leftContainer.addView(view);
            return  true;
        }
        return false;
    }

    public void hideLeftDrawer(){
        if(!isLeftDrawerNull){
            //TODO hide left drawer
        }
    }

    public void hideRightDrawer(){
        if(!isRightDrawerNull){
            //TODO hide left drawer
        }
    }

    private boolean initRightDrawer(){
        View view = getRightDrawer();
        if(view != null){
            isRightDrawerNull = false;
            FrameLayout rightContainer = (FrameLayout) findViewById(R.id.right_drawer);
            rightContainer.addView(view);
            return  true;
        }
        return false;
    }

    private void initActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
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

//    public int getContainer(){
//        return R.id.content_frame;
//    }
//
//    public int getLeftDrawerId(){
//        return R.id.left_drawer;
//    }
//
//    public int getRightDrawerId(){
//        return R.id.right_drawer;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void initPagerSlidingTabStrip() {
        // Initialize the ViewPager and set an adapter
        fragments = new ArrayList<>();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        List<Fragments> frag = getFragments();
        if(frag!=null&&frag.size()>0){
            fragments.addAll(frag);
        }
        adapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
        pager.setAdapter(adapter);

        // Bind the tabs to the ViewPager
        tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setIndicatorColor(getResources().getColor(R.color.bg_red));
        tabs.setIndicatorHeight(5);
        tabs.setTextColorResource(R.color.bg_red);
    }

    public void isHideTabs(boolean isHide){
        if(tabs!=null){
            if(isHide){
                tabs.setVisibility(View.GONE);
            }else{
                tabs.setVisibility(View.VISIBLE);
            }
        }
    }


    /**
     *
     * @return
     */
    protected abstract List<Fragments> getFragments();

    public void notifyFragmentsChanged(){
        fragments.clear();
        List<Fragments> frag = getFragments();
        if(frag!=null&&frag.size()>0){
            fragments.addAll(getFragments());
        }
        adapter.notifyDataSetChanged();
    }

    protected abstract View getLeftDrawer();
    protected abstract View getRightDrawer();


}
