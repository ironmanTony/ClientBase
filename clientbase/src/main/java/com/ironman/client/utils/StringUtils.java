package com.ironman.client.utils;

/**
 * Created by Administrator on 2015/5/19.
 */
public class StringUtils {

    public static boolean isNotNullOrEmpty(String str){
        if(str != null && str.length() > 0){
            return true;
        }
        return false;
    }
}
