package com.qunar.ironman.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.qunar.ironman.AppController;
import com.qunar.ironman.R;
import com.qunar.ironman.bean.News;
import com.qunar.ironman.event.EventGoDetail;
import com.qunar.ironman.view.adapter.AdapterMainPager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.common.view.DropDownListView;


public class FragmentPager extends Fragment {
    private boolean isBottomRunning = false;
    private DropDownListView dropDownListView;
    private ArrayList<News> data;
    private AdapterMainPager adapter;
    private int currentPage = 0;
    private final int PAGE_SIZE = 20;

    public static FragmentPager newInstance() {
        FragmentPager fragment = new FragmentPager();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentPager() {
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
        View view = inflater.inflate(R.layout.fragment_pager, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        dropDownListView = (DropDownListView) view.findViewById(R.id.pager_dropdown_list);
        dropDownListView.setOnDropDownListener(new DropDownListView.OnDropDownListener() {
            @Override
            public void onDropDown() {
                AppController.getInstance().addToRequestQueue(getJsonObjectRequest(getUrl(true)));
            }
        });
        dropDownListView.setOnBottomListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isBottomRunning){
                    isBottomRunning = true;
                    currentPage ++;
                    AppController.getInstance().addToRequestQueue(getJsonObjectBottomRequest(getUrl(false)));
                }
            }
        });
        data = new ArrayList<>();
        adapter = new AdapterMainPager(getActivity().getApplicationContext(), data, AppController.getInstance().getImageLoader());
        dropDownListView.setAdapter(adapter);
        //init data;
        dropDownListView.onDropDown();

    }

    private JsonObjectRequest getJsonObjectRequest(String url) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<News> d = JSON.parseObject(response.getString("T1348647909107").toString(), new TypeReference<List<News>>() {
                            }.getType());
                            if (d != null && d.size() > 0) {
                                data.clear();
                                data.addAll(d);
                                data.trimToSize();
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            Log.e("Parse Json error :", e.toString());
                        } finally {
                            dropDownListView.onDropDownComplete();
                        }
                        Log.d("volley", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse:", error.toString());
                        dropDownListView.onDropDownComplete();
                    }
                });
        return jsObjRequest;
    }

    private JsonObjectRequest getJsonObjectBottomRequest(String url) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<News> d = JSON.parseObject(response.getString("T1348647909107").toString(), new TypeReference<List<News>>() {
                            }.getType());
                            if (d != null && d.size() > 0) {
                                data.addAll(d);
                                adapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            Log.e("Parse Json error :", e.toString());
                        } finally {
                            isBottomRunning = false;
                        }
                        Log.d("volley", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("onErrorResponse:", error.toString());
                        isBottomRunning =false;
                        dropDownListView.onDropDownComplete();
                    }
                });
        return jsObjRequest;
    }


    private String getUrl(int start, int end) {
        return new StringBuilder("http://c.3g.163.com/nc/article/headline/T1348647909107/")
                .append(start)
                .append("-")
                .append(end)
                .append(".html").toString();
    }

    private String getUrl(boolean isPull) {
        if(isPull){
            currentPage = 0;
        }else{
            currentPage ++ ;
        }
        int start = currentPage*PAGE_SIZE;
        int end = PAGE_SIZE + start - 1;
        return new StringBuilder("http://c.3g.163.com/nc/article/headline/T1348647909107/")
                .append(start)
                .append("-")
                .append(end)
                .append(".html").toString();
    }



}
