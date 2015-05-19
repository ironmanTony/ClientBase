package com.ironman.client;

import com.ironman.client.tools.ParamsTools;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2015/5/18.
 */
public class TestParamsTool {

    @Test
    public void test_parmas_tools(){
        TestParamsObject object  = new TestParamsObject();
        object.x = 1;
        object.xxx = 3;
        object.setFuck("fff");
        object.setY(0.222f);
        System.out.println(ParamsTools.toUrl(object));
    }

    @Test
    public void test_params_map(){
        Map<String, String> map = new HashMap<>();
        map.put("fff", "23");
        map.put("89", "zhongguo");
        System.out.println(ParamsTools.mapToUrl(map));
    }
}
