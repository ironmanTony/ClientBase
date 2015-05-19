package com.ironman.client.tools;

import android.util.Log;


import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2015/5/18.
 */
public class ParamsTools {

    public static final String TAG = ParamsTools.class.getName();

    @SuppressWarnings("unchecked")
    public static String toUrl(Object obj, String str){
        if (obj == null) {
            return "";
        }
        if(str == null){
            return "";
        }
        StringBuilder sb = new StringBuilder(156);
        Field[] fields = obj.getClass().getDeclaredFields();
        try{
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                sb.append(fields[j].getName()).append("=");
                if(fields[j].getType().getName().equals(String.class.getName())){
                    sb.append(URLEncoder.encode(fields[j].get(obj).toString(), "utf-8"));
                }else{
                    sb.append(fields[j].get(obj));
                }
                sb.append(str);
            }
            sb.setLength(sb.length()-str.length());
        }catch (IllegalArgumentException | IllegalAccessException  | UnsupportedEncodingException e) {
            Log.e(TAG, e.toString());
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static String toUrl(Object obj){
        return toUrl(obj, "&");
    }
}
