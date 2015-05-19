package com.qunar.ironman.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.qunar.ironman.R;
import com.qunar.ironman.view.adapter.PagerAdapter;


public class FragmentContent extends Fragment {



    public static FragmentContent newInstance() {
        FragmentContent fragment = new FragmentContent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentContent() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        // Initialize the ViewPager and set an adapter
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new PagerAdapter(getFragmentManager(), getResources().getStringArray(R.array.tips)));

        // Bind the tabs to the ViewPager
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        tabs.setIndicatorColor(getResources().getColor(R.color.bg_red));
        tabs.setIndicatorHeight(5);
        tabs.setTextColorResource(R.color.bg_red);

    }



}
